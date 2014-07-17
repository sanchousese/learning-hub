package ua.com.learninghub.database.dao;

import ua.com.learninghub.database.entities.User;
import ua.com.learninghub.database.entities.UserCategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vasax32 on 15.07.14.
 */
public class UserCategoryDao {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

    public List<UserCategory> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT usr FROM User usr");
        List<UserCategory> categories = query.getResultList();
        entityManager.close();
        return categories;
    }

    public UserCategory selectById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT uCat FROM UserCategory uCat WHERE uCat.idUserCategory = :c_id");
        query.setParameter("c_id",id);
        UserCategory category = (UserCategory)query.getSingleResult();
        entityManager.close();
        return category;
    }

    public int update(UserCategory category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserCategory categoryUpd = (UserCategory) entityManager.find(UserCategory.class, category.getIdUserCategory());

        if (categoryUpd == null) return 0;

        entityManager.getTransaction().begin();
        categoryUpd.setName(category.getName());
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;
    }

    public void insert(UserCategory category){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
