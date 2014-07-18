package ua.com.learninghub.database.dao;

import ua.com.learninghub.database.entities.Course;
import ua.com.learninghub.database.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class CourseDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    public List<Course> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT courses FROM Course courses");
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }

    public Course selectById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT crs FROM Course crs WHERE crs.idCourse = :u_id");
        query.setParameter("u_id",id);
        Course course = (Course)query.getSingleResult();
        entityManager.close();
        return course;
    }

    public int update(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course1Upd = (Course) entityManager.find(Course.class, course.getIdCourse());

        if (course1Upd == null) return 0;

        entityManager.getTransaction().begin();
        course1Upd.setName(course.getName());
        course1Upd.setBeginDate(course.getBeginDate());
        course1Upd.setDescription(course.getDescription());
        course1Upd.setEndDate(course.getEndDate());
        course1Upd.setPrice(course.getPrice());
        course1Upd.setRate(course.getPrice());
        course1Upd.setSubject(course.getSubject());
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;
    }

    public void insert(Course course){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
