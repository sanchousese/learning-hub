package ua.com.learninghub.database.tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import ua.com.learninghub.database.dao.UserCategoryDao;
import ua.com.learninghub.database.dao.UserDao;
import ua.com.learninghub.database.entities.User;
import ua.com.learninghub.database.entities.UserCategory;

import java.util.List;

/**
 * Created by vasax32 on 15.07.14.
 */
public class UserTests {
    UserDao userDao = new UserDao();

    @Test
    public void selectAll(){
        List<User> list = userDao.selectAll();
        for (User usr: list)
            System.out.println(usr);
    }

    @Test
    public void selectByIdTest(){
        User user = userDao.selectById(1);
        System.out.println(user);
    }

    @Test
    public void updateUsersCategoryTest(){
        User user = userDao.selectById(1);
        user.setCategory((new UserCategoryDao().selectById(1)));
        userDao.update(user);
    }

    @Test
    public void insertFullyNewUser(){
        User user = new User();
        user.setLogin("Insert OK");
        user.setPass("SomePass");
        user.setEmail("some@mail.com");
        user.setMoney(1000);
        user.setCategory((new UserCategoryDao().selectById(2)));
        userDao.insert(user);
    }

    @Test
    public void findTestExist(){
        User urs = userDao.findByLoginPass("root", "pook");
        Assert.assertEquals(urs.getEmail(), "mail@mail.com");
    }

    @Test
    public void findTestNotExist(){
        User urs = userDao.findByLoginPass("root", "pook2");
        Assert.assertNull(urs);
    }
}
