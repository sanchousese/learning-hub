package ua.com.learninghub.dao;

import org.junit.Test;
import ua.com.learninghub.model.dao.UserCategoryDao;
import ua.com.learninghub.model.entities.UserCategory;

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
