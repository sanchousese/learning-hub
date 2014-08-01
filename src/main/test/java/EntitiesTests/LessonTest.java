package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.implementation.LessonDaoImpl;
import ua.com.learninghub.model.dao.interfaces.LessonDao;
import ua.com.learninghub.model.entities.Lesson;

import java.util.List;

/**
 * Created by vasax32 on 01.08.14.
 */
public class LessonTest {

    private LessonDao lessonDao = new LessonDaoImpl();

    @Test
    public void selectAll(){
        List<Lesson> lessons = lessonDao.selectAll();
        for (Lesson lesson: lessons)
            System.out.println(lesson);
    }

}
