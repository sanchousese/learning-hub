package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.implementation.LessonDaoImpl;
import ua.com.learninghub.model.dao.implementation.UserLessonDaoImpl;
import ua.com.learninghub.model.dao.implementation.UserDaoImpl;
import ua.com.learninghub.model.dao.interfaces.LessonDao;
import ua.com.learninghub.model.dao.interfaces.UserDao;
import ua.com.learninghub.model.dao.interfaces.UserLessonDao;
import ua.com.learninghub.model.entities.UserLesson;

import java.util.List;

/**
 * Created by vasax32 on 06.08.14.
 */
public class UserLessonTest {

    private UserLessonDao userLessonDao = new UserLessonDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private LessonDao lessonDao = new LessonDaoImpl();

    @Test
    public void selectAll(){
        List<UserLesson> userLessons = userLessonDao.selectAll();
        for (UserLesson userLesson : userLessons)
            System.out.println(userLesson);
    }

    @Test
    public void insert(){
        UserLesson userLesson = new UserLesson();
        userLesson.setScore(78.25);
        userLesson.setUser(userDao.selectById(1));
        userLesson.setLesson(lessonDao.selectById(1));
        userLessonDao.insert(userLesson);
    }
}
