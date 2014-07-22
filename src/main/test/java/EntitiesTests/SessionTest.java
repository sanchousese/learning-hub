package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.implementation.SessionDaoImpl;
import ua.com.learninghub.model.dao.implementation.UserDaoImpl;
import ua.com.learninghub.model.entities.Session;

import java.util.List;

/**
 * Created by vasax32 on 22.07.14.
 */
public class SessionTest {

    private SessionDaoImpl sessionDaoImpl = new SessionDaoImpl();

    @Test
    public void selectAll(){
        List<Session> sessions = sessionDaoImpl.selectAll();
        for(Session s : sessions)
            System.out.println(s);
    }

    @Test
    public void addSession() throws Exception {
        Session session = new Session("59874385399", null);
        //session.setCreateTime(new Timestamp(10000000L));
        //session.setLastAccessedTime(new Timestamp(10000000L));
        session.setUser((new UserDaoImpl()).selectById(1));
        System.out.println(sessionDaoImpl.insert(session));
    }

    @Test
    public void deleteSession(){
        System.out.println(sessionDaoImpl.deleteBySessionId("59874385399"));
    }
}
