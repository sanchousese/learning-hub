package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Session;

import java.util.List;

/**
 * Created by vasax32 on 22.07.14.
 */
public interface SessionDao {
    public List<Session> selectAll();

    public Session selectBySessionId(String sessionId);

    public boolean insert(Session session);

    public boolean deleteBySessionId(String sessionId);
}
