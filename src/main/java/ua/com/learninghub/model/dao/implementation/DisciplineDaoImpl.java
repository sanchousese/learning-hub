package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.DisciplineDao;
import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 24.07.14.
 */
public class DisciplineDaoImpl implements DisciplineDao{

    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    @Override
    public List<Discipline> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT disp FROM Discipline disp");
        List<Discipline> disciplines = query.getResultList();
        entityManager.close();
        return disciplines;
    }

    @Override
    public Discipline selectById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT disp FROM Discipline disp WHERE disp.idDiscipline = :d_id");
        query.setParameter("d_id", id);
        Discipline discipline = null;
        try {
            discipline = (Discipline) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        finally {
            entityManager.close();
        }
        return discipline;
    }

    @Override
    public boolean update(Discipline discipline) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Discipline disciplineUpd = (Discipline) entityManager.find(Discipline.class, discipline.getIdDiscipline());

        if (disciplineUpd == null) return false;

        entityManager.getTransaction().begin();
        disciplineUpd.setName(discipline.getName());
        disciplineUpd.setDescription(discipline.getDescription());
        disciplineUpd.setSpecialty(discipline.getSpecialty());
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean insert(Discipline discipline){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(discipline);
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
