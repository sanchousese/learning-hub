package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.SpecialtyDao;
import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.Specialty;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 25.07.14.
 */
public class SpecialtyDaoImpl implements SpecialtyDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    @Override
    public List<Specialty> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT spec FROM Specialty spec");
        List<Specialty> specialties = query.getResultList();
        entityManager.close();
        return specialties;
    }

    @Override
    public Specialty selectById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT spec FROM Specialty spec WHERE spec.idSpecialty = :s_id");
        query.setParameter("s_id", id);
        Specialty specialty = null;
        try {
            specialty = (Specialty) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        finally {
            entityManager.close();
        }
        return specialty;
    }

    @Override
    public boolean update(Specialty specialty) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Specialty specialtyUpd = (Specialty) entityManager.find(Specialty.class, specialty.getIdSpecialty());

        if (specialtyUpd == null) return false;

        entityManager.getTransaction().begin();
        specialtyUpd.setName(specialty.getName());
        specialtyUpd.setDescription(specialty.getDescription());
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean insert(Specialty specialty){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(specialty);
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
}
