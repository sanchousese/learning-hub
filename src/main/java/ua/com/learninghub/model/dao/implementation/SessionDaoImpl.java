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

    private Timestamp diff (java.util.Date t1, java.util.Date t2)
    {
        // Make sure the result is always > 0
        if (t1.compareTo (t2) < 0)
        {
            java.util.Date tmp = t1;
            t1 = t2;
            t2 = tmp;
        }

        // Timestamps mix milli and nanoseconds in the API, so we have to separate the two
        long diffSeconds = (t1.getTime () / 1000) - (t2.getTime () / 1000);
        // For normals dates, we have millisecond precision
        int nano1 = ((int) t1.getTime () % 1000) * 1000000;
        // If the parameter is a Timestamp, we have additional precision in nanoseconds
        if (t1 instanceof Timestamp)
            nano1 = ((Timestamp)t1).getNanos ();
        int nano2 = ((int) t2.getTime () % 1000) * 1000000;
        if (t2 instanceof Timestamp)
            nano2 = ((Timestamp)t2).getNanos ();

        int diffNanos = nano1 - nano2;
        if (diffNanos < 0)
        {
            // Borrow one second
            diffSeconds --;
            diffNanos += 1000000000;
        }

        // mix nanos and millis again
        Timestamp result = new Timestamp ((diffSeconds * 1000) + (diffNanos / 1000000));
        // setNanos() with a value of in the millisecond range doesn't affect the value of the time field
        // while milliseconds in the time field will modify nanos! Damn, this API is a *mess*
        result.setNanos (diffNanos);
        return result;
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
