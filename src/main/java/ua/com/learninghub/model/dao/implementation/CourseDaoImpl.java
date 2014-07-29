package ua.com.learninghub.model.dao.implementation;

import ua.com.learninghub.model.dao.HibernateUtil;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;
import ua.com.learninghub.model.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class CourseDaoImpl implements CourseDao, HibernateL2Cache{
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.buildEntityManagerFactory();

//============================SEARCH METHODS=============================


    @Override
    public List<Course> findByConstraints(CourseSearch search) {

        CourseSearchType searchType = search.getSearchType();
        switch (searchType) {
            case SEARCH_BY_RANGES:
                return selectById(search.getIdFrom(), search.getIdTo());

            case SEARCH_BY_KEYWORDS:
                return selectByKeywords(search.getKeywords());

            case SEARCH_BY_SPECIALITY:
                return selectBySpeciality(search.getIdSpeciality());
            case SEARCH_BY_DISCIPLINE:
                return selectByDiscipline(search.getIdDiscipline());
            case SEARCH_BY_SUBJECT:
                return selectBySubject(search.getIdSubject());
        }

        return null;
    }

    @Override
    public List<Course> selectByDiscipline(int disID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Discipline discipline = (new DisciplineDaoImpl()).selectById(disID);

        List<Subject> subjectList = discipline.getSubjects();

        List<Course> courses = new ArrayList<Course>();
        for (Subject s : subjectList) {
            courses.addAll(selectBySubject(s.getIdSubject()));
        }
        return courses;
    }

    @Override
    public List<Course> selectBySpeciality(int specID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Specialty speciality = (new SpecialtyDaoImpl()).selectById(specID);
        List<Discipline> disciplineList  = speciality.getDisciplines();

        List<Subject> subjectList = new ArrayList<Subject>();
        for (Discipline d : disciplineList) {
            subjectList.addAll(d.getSubjects());
        }

        List<Course> courses = new ArrayList<Course>();
        for (Subject s : subjectList) {
            courses.addAll(selectBySubject(s.getIdSubject()));
        }
        return courses;
    }

    // select courses by subject
    @Override
    public List<Course> selectBySubject(int subjID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // SELECT * FROM learningdb.course WHERE course.idSubject = 1;
        Query query = entityManager.createQuery("SELECT courses FROM Course courses " +
                "WHERE courses.subject.idSubject = :subjId");
        query.setParameter("subjId", subjID);
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }

    //select courses from idFrom to idTo
    @Override
    public List<Course> selectById(int idFrom, int idTo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT courses FROM Course courses");
        query.setFirstResult(idFrom);
        query.setMaxResults(idTo);
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }
/*
    class KeywordsList<String> extends ArrayList<String> {
        ArrayList<String> keywords;
        KeywordsList(List<String> kWords) {
            keywords = new ArrayList<String>(kWords);
        }

        @Override
        public java.lang.String toString() {
            StringBuilder sb = new StringBuilder();
            for (String keyword: keywords) {

            }
        }
    }*/
    //select courses started with sequence of keywords
    @Override
    public List<Course> selectByKeywords(List <String> keywords) {

        StringBuilder wordsParam = new StringBuilder();
        formWordsParam(keywords, wordsParam);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //SELECT DISTINCT * FROM learningdb.course
        // where learningdb.course.name like "%ver%" or learningdb.course.description like "%ver%";

        Query query = entityManager.createQuery("SELECT DISTINCT courses FROM Course courses WHERE " + wordsParam);
        //query.setParameter("keywordsParam",wordsParam.toString());
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }

    private void formWordsParam(List<String> keywords, StringBuilder wordsParam) {
        int i = 0;
        int stop = keywords.size() - 1;
        for (String keyword: keywords) {
            keyword = "%" + keyword + "%";

            String nextPartOfQuery;
            if (i != stop) {
                nextPartOfQuery = String.format("courses.name LIKE '%s' OR courses.description LIKE '%s' OR ", keyword, keyword);
            }
            else {
                nextPartOfQuery = String.format("courses.name LIKE '%s' OR courses.description LIKE '%s'", keyword, keyword);
            }
            wordsParam.append(nextPartOfQuery);
            i++;
        }
    }

    //=====================================================================


    @Override
    public List<Course> selectAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT courses FROM Course courses");
        List<Course> courses = query.getResultList();
        entityManager.close();
        return courses;
    }

    @Override
    public Course selectById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT crs FROM Course crs WHERE crs.idCourse = :u_id");
        query.setParameter("u_id",id);
        try {
            Course course = (Course) query.getSingleResult();
            return course;
        } catch (NoResultException e){
            return null;
        } finally {
            entityManager.close();
        }
}

    @Override
    public boolean update(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course1Upd = (Course) entityManager.find(Course.class, course.getIdCourse());

        if (course1Upd == null) return false;

        try {
            entityManager.getTransaction().begin();
            course1Upd.setName(course.getName());
            //course1Upd.setBeginDate(course.getBeginDate());
            course1Upd.setDescription(course.getDescription());
            //course1Upd.setEndDate(course.getEndDate());
            course1Upd.setPrice(course.getPrice());
            course1Upd.setRate(course.getPrice());
            course1Upd.setMainImagePath(course.getMainImagePath());
            course1Upd.setMainVideoPath(course.getMainVideoPath());
            course1Upd.setSubject(course.getSubject());
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public boolean insert(Course course){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(course);
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

    @Override
    public void delete(int courseId) {
        //delete from course where courseid = ?
    }

}
