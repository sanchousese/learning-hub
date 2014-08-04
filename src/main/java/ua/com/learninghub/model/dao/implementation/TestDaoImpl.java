package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;
import ua.com.learninghub.model.dao.interfaces.SubjectDao;
import ua.com.learninghub.model.dao.interfaces.TestDao;
import ua.com.learninghub.model.entities.Subject;
import ua.com.learninghub.model.entities.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class TestDaoImpl implements TestDao, HibernateL2Cache {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();


    @Override
    public List<Test> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT tes FROM Test tes");
        List<Test> tests = query.getResultList();
        entityManager.close();
        return tests;
    }

    @Override
    public Test selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT tes FROM Test tes WHERE tes.idTest = :t_id");
        query.setParameter("t_id", id);
        Test test = (Test) query.getSingleResult();
        entityManager.close();
        return test;
    }

    @Override
    public boolean update(Test test) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Test testUpd = (Test) entityManager.find(Test.class, test.getIdTest());

        if (testUpd == null) return false;

        try {
            entityManager.getTransaction().begin();
            testUpd.setName(test.getName());
            testUpd.setModule(test.getModule());
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean insert(Test test) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(test);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            return false;
        }finally {
            entityManager.close();
        }
    }
}