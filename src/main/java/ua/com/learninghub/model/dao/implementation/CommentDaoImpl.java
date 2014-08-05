package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.CommentDao;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.entities.Comment;
import ua.com.learninghub.model.entities.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Max on 05.08.2014.
 */
public class CommentDaoImpl implements CommentDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    @Override
    public List<Comment> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT comments FROM Comment comments");
        List<Comment> comments = query.getResultList();
        entityManager.close();
        return comments;
    }

    @Override
    public Comment selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT comment FROM Comment comment WHERE comment.idComment = :idComm");
        query.setParameter("idComm", id);
        Comment comment = (Comment) query.getSingleResult();
        return comment;
    }

    @Override
    public boolean update(Comment comment){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Comment commentUpd = (Comment) entityManager.find(Comment.class, comment.getIdComment());

        if (commentUpd == null) return false;

        try {
            entityManager.getTransaction().begin();
            commentUpd.setBody(comment.getBody());
            commentUpd.setCourse(comment.getCourse());
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean insert(Comment comment) {
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
