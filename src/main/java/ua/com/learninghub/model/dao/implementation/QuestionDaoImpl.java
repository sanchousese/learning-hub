package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.QuestionDao;
import ua.com.learninghub.model.entities.Question;
import ua.com.learninghub.model.entities.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
public class QuestionDaoImpl implements QuestionDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    @Override
    public List<Question> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT q FROM Question q");
        List<Question> questions = query.getResultList();
        entityManager.close();
        return questions;
    }

    @Override
    public Question selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT que FROM Question que WHERE que.idQuestion = :t_id");
        query.setParameter("t_id", id);
        Question question = (Question) query.getSingleResult();
        entityManager.close();
        return question;
    }

    @Override
    public boolean update(Question question) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Question questionUpd = (Question) entityManager.find(Question.class, question.getIdQuestion());

        if (questionUpd == null) return false;

        try {
            entityManager.getTransaction().begin();
            questionUpd.setQue(question.getQue());
            questionUpd.setTest(question.getTest());
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public boolean insert(Question question) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(question);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }
    }
}
