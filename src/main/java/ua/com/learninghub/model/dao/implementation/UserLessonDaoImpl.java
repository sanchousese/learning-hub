package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.AnswerDao;
import ua.com.learninghub.model.dao.interfaces.UserLessonDao;
import ua.com.learninghub.model.entities.Answer;
import ua.com.learninghub.model.entities.UserLesson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
public class UserLessonDaoImpl implements UserLessonDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    @Override
    public List<UserLesson> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT a FROM Answer a");
        List<UserLesson> userLessons = query.getResultList();
        entityManager.close();
        return userLessons;
    }

    @Override
    public UserLesson selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT usrl FROM UserLesson usrl WHERE usrl.idUserLesson = :ul_id");
        query.setParameter("ul_id", id);
        UserLesson userLesson = (UserLesson) query.getSingleResult();
        entityManager.close();
        return userLesson;
    }

    @Override
    public boolean update(UserLesson userLesson) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserLesson userLessonUpd = (UserLesson) entityManager.find(UserLesson.class, userLesson.getIdUserLesson());

        if (userLessonUpd == null) return false;

        try {
            entityManager.getTransaction().begin();
            userLessonUpd.setScore(userLesson.getScore());
            userLessonUpd.setLesson(userLesson.getLesson());
            userLessonUpd.setUser(userLesson.getUser());
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public boolean insert(UserLesson userLesson) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userLesson);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }
    }

    public boolean selectByUserIdLessonId(int lessonId, int userId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT usrl FROM UserLesson usrl WHERE usrl.user.idUser = :u_id and usrl.lesson.idLesson = :l_id");
        query.setParameter("u_id", userId);
        query.setParameter("l_id", lessonId);
        try {
            UserLesson userLesson = (UserLesson) query.getSingleResult();
            return true;
        } catch (NoResultException e){
            return false;
        } finally {
            entityManager.close();
        }
    }
}

