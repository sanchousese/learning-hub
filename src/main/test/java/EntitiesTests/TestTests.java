package EntitiesTests;

import ua.com.learninghub.model.dao.implementation.LessonDaoImpl;
import ua.com.learninghub.model.dao.implementation.TestDaoImpl;
import ua.com.learninghub.model.dao.interfaces.LessonDao;
import ua.com.learninghub.model.dao.interfaces.TestDao;
import ua.com.learninghub.model.entities.Lesson;
import ua.com.learninghub.model.entities.Test;

import java.util.List;
/**
 * Created by vasax32 on 01.08.14.
 */
public class TestTests {

    private TestDao testDao = new TestDaoImpl();

    LessonDao lessonDao = new LessonDaoImpl();

    @org.junit.Test
    public void selectAll(){
        List<Test> tests = testDao.selectAll();
        for (Test test : tests)
            System.out.println(test);
    }
/*

    @org.junit.Test
    public void insert(){
        Test test = new Test();

        test.setIdTest(33);
        test.setName("Insert TEST");
        test.setLesson(new Lesson());
        //test.setModule((new ModuleDaoImpl()).selectById(1));
        testDao.insert(test);
    }
*/

    @org.junit.Test
    public void update(){
        Test test = testDao.selectById(2);
        test.setName("Update OK");
        //test.setModule((new ModuleDaoImpl()).selectById(2));
        testDao.update(test);
    }

}
