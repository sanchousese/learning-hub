package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.UserCategory;

import java.util.List;

/**
 * Created by Max on 21.07.2014.
 */
public interface UserCategoryDao {
    List<UserCategory> selectAll();

    UserCategory selectById(int id);

    int update(UserCategory category);

    void insert(UserCategory category);
}
