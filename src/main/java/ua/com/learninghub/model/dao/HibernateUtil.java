package ua.com.learninghub.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by vasax32 on 15.07.14.
 */
public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory = null;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("MySqlManager");
    }

    public static EntityManagerFactory buildEntityManagerFactory(){
        return entityManagerFactory;
    }

    private HibernateUtil(){

    }

}
