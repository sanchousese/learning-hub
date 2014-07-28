package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.Subject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class CourseDaoImpl implements CourseDao, HibernateL2Cache{
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    public List<Course> selectBySubject(Subject subj) {
    return null;
    }
    /*@Override
    public List<Course> selectBySubject(Subject subj) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //SELECT DISTINCT * FROM learningdb.course
        // where learningdb.course.name like "%ver%" or learningdb.course.description like "%ver%";


        Query query = entityManager.createQuery("SELECT courses FROM Course courses " +
                "WHERE courses.subject = :subjParam");
        query.setParameter("subjParam", subj);
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }
*/
    //select courses from idFrom to idTo
    @Override
    public List<Course> selectById(int idFrom, int idTo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT courses FROM Course courses");
        query.setFirstResult(idFrom);
        query.setMaxResults(idTo);
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }

    //select courses started with "cName" string
    @Override
    public List<Course> selectByName(String cName) {
        String nameParam = "%" + cName + "%";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //SELECT DISTINCT * FROM learningdb.course
        // where learningdb.course.name like "%ver%" or learningdb.course.description like "%ver%";

        Query query = entityManager.createQuery("SELECT DISTINCT courses FROM Course courses " +
                "WHERE courses.name  LIKE :namePar OR courses.description LIKE :namePar");
        query.setParameter("namePar",nameParam);
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }

    @Override
    public List<Course> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT courses FROM Course courses");
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }

    @Override
    public Course selectById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT crs FROM Course crs WHERE crs.idCourse = :u_id");
        query.setParameter("u_id",id);
        Course course = (Course)query.getSingleResult();
        entityManager.close();
        return course;
    }

    @Override
    public Course update(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course1Upd = (Course) entityManager.find(Course.class, course.getIdCourse());

        if (course1Upd == null) return null;

        entityManager.getTransaction().begin();
        course1Upd.setName(course.getName());
        //course1Upd.setBeginDate(course.getBeginDate());
        course1Upd.setDescription(course.getDescription());
        //course1Upd.setEndDate(course.getEndDate());
        course1Upd.setPrice(course.getPrice());
        course1Upd.setRate(course.getPrice());
        course1Upd.setMainImagePath(course.getMainImagePath());
        course1Upd.setSubject(course.getSubject());
        entityManager.getTransaction().commit();
        entityManager.close();
        return course1Upd;
    }

    @Override
    public boolean insert(Course course){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(course);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            return false;
        }
        finally{
            entityManager.close();
        }
        return true;
    }

    @Override
    public void delete(int courseId) {
        //delete from course where courseid = ?
    }

}
