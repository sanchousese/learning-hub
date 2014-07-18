package ua.com.learninghub.dao;

import org.junit.Test;
import ua.com.learninghub.model.dao.CourseDao;
import ua.com.learninghub.model.dao.SubjectDao;
import ua.com.learninghub.model.entities.Course;

import java.sql.Date;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class CourseTest {
    private CourseDao courseDao = new CourseDao();

    @Test
    public void selectAll(){
        List<Course> courses = courseDao.selectAll();
        System.out.println(courses);
    }

    @Test
    public void insertCourse(){
        Course course = new Course();
        course.setName("PHP");
        course.setBeginDate(new Date(2014, 5, 28));
        course.setDescription("Some very good description");
        course.setEndDate(new Date(2014, 7, 28));
        course.setPrice(50);
        course.setRate(5);
        course.setSubject((new SubjectDao()).selectById(1));
        courseDao.insert(course);
    }

    @Test
    public void updateCourse(){
        Course course = courseDao.selectById(1);
        course.setSubject((new SubjectDao()).selectById(2));
        courseDao.update(course);

        List<Course> courses = (new SubjectDao()).selectById(2).getCourses();
        System.out.println(courses);
    }


}
