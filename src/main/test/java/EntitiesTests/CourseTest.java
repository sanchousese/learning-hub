package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.implementation.SubjectDaoImpl;
import ua.com.learninghub.model.dao.implementation.UserDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        for (Course course : courses)
            System.out.println(course.getUsers());
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

    @Test
    public void addUserOld(){
        User user = (new UserDaoImpl()).selectById(1);
        Course course = courseDao.selectById(4);
        List<User> users = course.getUsers();
        users.add(user);
        course.setUsers(users);
        courseDao.update(course);
    }


    @Test
    public void addUser(){
        User user = (new UserDaoImpl()).selectById(3); // user - 3
        EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = (Course) entityManager.find(Course.class, 4); //course - 4
        courseDao.addUser(course, user);
    }

    @Test
    public void contains(){
        User user = (new UserDaoImpl()).selectById(3); // user - 3
        EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = (Course) entityManager.find(Course.class, 4); //course - 4
        System.out.println(courseDao.checkUser(course, user));
    }

}
