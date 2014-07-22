package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.SessionDao;
import ua.com.learninghub.model.dao.UserDao;
import ua.com.learninghub.model.entities.Session;
import ua.com.learninghub.model.entities.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by vasax32 on 22.07.14.
 */
public class SessionTest {

    private SessionDao sessionDao = new SessionDao();

    @Test
    public void selectAll(){
        List<Session> sessions = sessionDao.selectAll();
        for(Session s : sessions)
            System.out.println(s);
    }

    @Test
    public void addSession() throws Exception {
        Session session = new Session("59874385399", null);
        //session.setCreateTime(new Timestamp(10000000L));
        //session.setLastAccessedTime(new Timestamp(10000000L));
        session.setUser((new UserDao()).selectById(1));
        System.out.println(sessionDao.insert(session));
    }

    @Test
    public void deleteSession(){
        System.out.println(sessionDao.deleteBySessionId("59874385399"));
    }
}
