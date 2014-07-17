package ua.com.learninghub.database.tests;

import org.junit.Test;
import ua.com.learninghub.database.dao.UserCategoryDao;
import ua.com.learninghub.database.dao.UserDao;
import ua.com.learninghub.database.entities.User;
import ua.com.learninghub.database.entities.UserCategory;

import java.util.List;

/**
 * Created by vasax32 on 15.07.14.
 */
public class UserCategoryTests {
    UserCategoryDao userCategoryDao = new UserCategoryDao();

    @Test
    public void updateUserCategory(){
        UserCategory category = userCategoryDao.selectById(1);
        category.setName("Admin OK");
        userCategoryDao.update(category);
    }

    @Test
    public void insertUserCategory(){
        UserCategory category = new UserCategory();
        category.setName("Insert OK");
        userCategoryDao.insert(category);
    }

    @Test
    public void selectBy(){
        UserCategory category = userCategoryDao.selectById(1);
        System.out.println(category);
    }
}
