package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.AnswerDao;
import ua.com.learninghub.model.entities.Answer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
public class AnswerDaoImpl implements AnswerDao{
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    @Override
    public List<Answer> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT a FROM Answer a");
        List<Answer> answers = query.getResultList();
        entityManager.close();
        return answers;
    }

    @Override
    public Answer selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT ans FROM Answer ans WHERE ans.idAnswer = :t_id");
        query.setParameter("t_id", id);
        Answer answer = (Answer) query.getSingleResult();
        entityManager.close();
        return answer;
    }

    @Override
    public boolean update(Answer answer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Answer answerUpd = (Answer) entityManager.find(Answer.class, answer.getIdAnswer());

        if (answerUpd == null) return false;

        try {
            entityManager.getTransaction().begin();
            answerUpd.setAns(answer.getAns());
            answerUpd.setCorrect(answer.getCorrect());
            answerUpd.setQuestion(answer.getQuestion());
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public boolean insert(Answer answer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(answer);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }
    }
}

