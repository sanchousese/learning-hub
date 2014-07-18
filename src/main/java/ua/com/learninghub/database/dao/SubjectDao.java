package ua.com.learninghub.database.dao;

import ua.com.learninghub.database.entities.Course;
import ua.com.learninghub.database.entities.Subject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class SubjectDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    public List<Subject> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT sbj FROM Subject sbj");
        List<Subject> subjects = query.getResultList();
        entityManager.close();
        return subjects;
    }

    public Subject selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT sbj FROM Subject sbj WHERE sbj.idSubject = :u_id");
        query.setParameter("u_id", id);
        Subject subject = (Subject) query.getSingleResult();
        entityManager.close();
        return subject;
    }

    public int update(Subject subject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Subject subjectUpd = (Subject) entityManager.find(Subject.class, subject.getIdSubject());

        if (subjectUpd == null) return 0;

        entityManager.getTransaction().begin();
        subjectUpd.setName(subject.getName());
        subjectUpd.setDescription(subject.getDescription());
        subjectUpd.setLogoPath(subjectUpd.getLogoPath());
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;
    }

    public void insert(Subject subject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(subject);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}