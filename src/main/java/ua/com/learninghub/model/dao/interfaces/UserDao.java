package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.User;

import java.util.List;

/**
 * Created by Max on 21.07.2014.
 */
public interface UserDao {
    List<User> selectAll();

    User selectById(int id);

    int update(User user);

    boolean insert(User user);

    User findByLoginPass(String login, String pass);
}
