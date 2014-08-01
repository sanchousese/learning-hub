package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;
import ua.com.learninghub.model.dao.interfaces.ModuleDao;
import ua.com.learninghub.model.dao.interfaces.SubjectDao;
import ua.com.learninghub.model.entities.Module;
import ua.com.learninghub.model.entities.Subject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class ModuleDaoImpl implements ModuleDao, HibernateL2Cache {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();


    @Override
    public List<Module> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT modul FROM Module modul");
        List<Module> modules = query.getResultList();
        entityManager.close();
        return modules;
    }

    @Override
    public Module selectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT module FROM Module module WHERE module.idModule = :m_id");
        query.setParameter("m_id", id);
        Module module = (Module) query.getSingleResult();
        entityManager.close();
        return module;
    }

    @Override
    public boolean update(Module module) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Module modelUpd = (Module) entityManager.find(Module.class, module.getIdModule());

        if (modelUpd == null) return false;

        try {
            entityManager.getTransaction().begin();
            modelUpd.setName(module.getName());
            modelUpd.setDescription(module.getDescription());
            modelUpd.setModuleImage(modelUpd.getModuleImage());
            modelUpd.setCourse(module.getCourse());
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            return false;
        }
        finally {
            entityManager.close();
            return true;
        }

    }

    @Override
    public boolean insert(Module module) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(module);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            return false;
        }
        finally {
            entityManager.close();
            return true;
        }

    }
}