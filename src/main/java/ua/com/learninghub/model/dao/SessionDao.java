package ua.com.learninghub.model.dao;

import ua.com.learninghub.model.entities.Session;
import ua.com.learninghub.model.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * Created by vasax32 on 22.07.14.
 */
public class SessionDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    public List<Session> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT ses FROM Session ses");
        List<Session> sessions = query.getResultList();
        entityManager.close();
        return sessions;
    }

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
}
