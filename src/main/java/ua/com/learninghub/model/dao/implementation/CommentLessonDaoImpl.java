package ua.com.learninghub.model.dao.implementation;

import org.hibernate.ejb.EntityManagerFactoryImpl;
import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.CommentLessonDao;
import ua.com.learninghub.model.entities.CommentLesson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Max on 06.08.2014.
 */
public class CommentLessonDaoImpl implements CommentLessonDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    @Override
    public List<CommentLesson> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT comments FROM CommentLesson comments");
        List<CommentLesson> comments = query.getResultList();
        entityManager.close();
        return comments;

    }

    @Override
    public CommentLesson selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT comment FROM CommentLesson comment WHERE comment.idCommentLesson = :idComm");
        query.setParameter("idComm", id);
        CommentLesson comment = (CommentLesson) query.getSingleResult();
        return comment;

    }

    @Override
    public boolean update(CommentLesson comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CommentLesson commentUpd = (CommentLesson) entityManager.find(CommentLesson.class, comment.getIdCommentLesson());

        if (commentUpd == null) return false;

        try {
            entityManager.getTransaction().begin();
            commentUpd.setBody(comment.getBody());
            commentUpd.setLesson(comment.getLesson());
            commentUpd.setDate(comment.getDate());
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean insert(CommentLesson comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(comment);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }
    }
}
