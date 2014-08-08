package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;
import ua.com.learninghub.model.dao.interfaces.SessionDao;
import ua.com.learninghub.model.entities.Session;
import ua.com.learninghub.model.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vasax32 on 22.07.14.
 */
public class SessionDaoImpl implements SessionDao, HibernateL2Cache {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    public List<Session> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT ses FROM Session ses");
        List<Session> sessions = query.getResultList();
        entityManager.close();
        return sessions;
    }

    //rest/user/login
    //rest/jerseyservice/login
    //rest/jerseyservice/user
    //rest/jerseyservice/checksession
    //rest/course/user


    public Session selectBySessionId(String sessionId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT ses FROM Session ses WHERE ses.sessionId = :s_id");
        query.setParameter("s_id", sessionId);
        Session session = null;
        try {
            session = (Session) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        finally {
            entityManager.close();
        }
        return session;
    }

    public void clearTimeout(long msec){
        List<Session> sessions = selectAll();
        for(int i = 0; i < sessions.size(); i++){
            long difference = (new Timestamp(Calendar.getInstance().getTime().getTime())).getNanos() - sessions.get(i).getLastAccessedTime().getNanos();
            if(difference > msec){
                deleteBySession(sessions.get(i));
            }
        }
    }

    public int update(Session session) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session sessionUpd = (Session) entityManager.find(Session.class, session.getSessionId());

        if (sessionUpd == null) return 0;

        entityManager.getTransaction().begin();
        sessionUpd.setSessionId(session.getSessionId());
        sessionUpd.setCreateTime(session.getCreateTime());
        sessionUpd.setLastAccessedTime(session.getLastAccessedTime());
        sessionUpd.setUser(session.getUser());
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;
    }

    public boolean insert(Session session){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(session);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            return false;
            //throw e;
        }
        finally{
            entityManager.close();
        }
        return true;
    }

    public boolean deleteBySessionId(String sessionId){
        Session session = selectBySessionId(sessionId);
        if(session == null) return false;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            //creating from detached entity managed
            Object managed = entityManager.merge(session);
            entityManager.remove(managed);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            return false;
        }
        finally{
            entityManager.close();
        }
        return true;
    }

    public boolean deleteBySession(Session session){
        if(session == null) return false;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            //creating from detached entity managed
            Object managed = entityManager.merge(session);
            entityManager.remove(managed);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            return false;
        }
        finally{
            entityManager.close();
        }
        return true;
    }
}
