package ua.com.learninghub.controller.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by vasax32 on 21.07.14.
 */
public class SessionRepository {
    private static ConcurrentLinkedDeque<Session> sessions = new ConcurrentLinkedDeque<Session>();

    public Session findOne(String sessionId){
        for(Session s : sessions)
            if(s.getSessionId().equals(sessionId))
                return s;
        return null;
    }

    public void add(Session s){
        if(s == null) return;
        int userId = s.getUserId();
        for(Session ss : sessions)
            if(ss.getUserId() == userId)
                sessions.remove(ss);
        sessions.add(s);
    }

    public void removeById(String sessionId){
        for(Session ss : sessions)
            if(ss.getSessionId().equals(sessionId))
                sessions.remove(ss);
    }
}
