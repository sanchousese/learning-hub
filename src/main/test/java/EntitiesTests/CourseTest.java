package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.implementation.SubjectDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.entities.Course;

import java.sql.Date;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class CourseTest {
    private CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void selectAll(){
        List<Course> courses = courseDao.selectAll();
        System.out.println(courses);
    }

    @Test
    public void insertCourse(){
        Course course = new Course();
        course.setName("PHP2");
        //course.setBeginDate(new Date(2014, 5, 28));
        course.setDescription("Some very good description");
        //course.setEndDate(new Date(2014, 7, 28));
        course.setPrice(50);
        course.setRate(5);
        course.setMainImagePath("img\\1.jpg");
        course.setSubject((new SubjectDaoImpl()).selectById(1));
        courseDao.insert(course);
        System.out.println(course.getIdCourse());
    }

    @Test
    public void updateCourse(){
        Course course = courseDao.selectById(1);
        course.setSubject((new SubjectDaoImpl()).selectById(2));
        courseDao.update(course);

        List<Course> courses = (new SubjectDaoImpl()).selectById(2).getCourses();
        System.out.println(courses);
    }


}
