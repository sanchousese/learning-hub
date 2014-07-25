package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;
import ua.com.learninghub.model.dao.interfaces.SubjectDao;
import ua.com.learninghub.model.entities.Subject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class SubjectDaoImpl implements SubjectDao, HibernateL2Cache {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    @Override
    public List<Subject> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT sbj FROM Subject sbj");
        List<Subject> subjects = query.getResultList();
        entityManager.close();
        return subjects;
    }

    @Override
    public Subject selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT sbj FROM Subject sbj WHERE sbj.idSubject = :u_id");
        query.setParameter("u_id", id);
        Subject subject = (Subject) query.getSingleResult();
        entityManager.close();
        return subject;
    }

    @Override
    public int update(Subject subject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Subject subjectUpd = (Subject) entityManager.find(Subject.class, subject.getIdSubject());

        if (subjectUpd == null) return 0;

        entityManager.getTransaction().begin();
        subjectUpd.setName(subject.getName());
        subjectUpd.setDescription(subject.getDescription());
        subjectUpd.setLogoPath(subjectUpd.getLogoPath());
        subjectUpd.setDiscipline(subject.getDiscipline());
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;
    }

    @Override
    public void insert(Subject subject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(subject);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}