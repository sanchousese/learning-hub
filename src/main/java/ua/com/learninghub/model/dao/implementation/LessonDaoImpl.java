package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;
import ua.com.learninghub.model.dao.interfaces.LessonDao;
import ua.com.learninghub.model.entities.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class LessonDaoImpl implements LessonDao, HibernateL2Cache {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();


    @Override
    public List<Lesson> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT les FROM Lesson  les");
        List<Lesson> lessons = query.getResultList();
        entityManager.close();
        return lessons;
    }

    @Override
    public Lesson selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT les FROM Lesson les WHERE les.idLesson = :l_id");
        query.setParameter("l_id", id);
        Lesson lesson = (Lesson) query.getSingleResult();
        entityManager.close();
        return lesson;
    }

    @Override
    public boolean update(Lesson lesson) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Lesson lessonUpd = (Lesson) entityManager.find(Lesson.class, lesson.getIdLesson());

        if (lessonUpd == null) return false;

        try {
            entityManager.getTransaction().begin();
            lessonUpd.setDescription(lesson.getDescription());
            lessonUpd.setLessonVideo(lessonUpd.getLessonVideo());
            lessonUpd.setTest(lesson.getTest());
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            return false;
        }
        finally {
            entityManager.close();
            return true;
        }

    }

    @Override
    public boolean insert(Lesson lesson) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(lesson);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            return false;
        }
        finally {
            entityManager.close();
            return true;
        }

    }
}