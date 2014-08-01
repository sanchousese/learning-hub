package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.CourseSearch;
import ua.com.learninghub.model.entities.Subject;
import ua.com.learninghub.model.entities.User;

import java.util.List;

/**
 * Created by Max on 21.07.2014.
 */
public interface CourseDao {

    List<Course> selectBySubject(int subjID);

    List<Course> selectAll();

    Course selectById(int id);

    boolean update(Course course);

    boolean insert(Course course);

    void delete(int courseId);

    List<Course> selectByKeywords(String keywords);

    List<Course> selectById(int idFrom, int idTo);


    List<Course> selectBySpeciality(int specID);

    List<Course> selectByDiscipline(int disID);

    List<Course> findByConstraints(CourseSearch search);

    public boolean addUser(Course course, User user);

    public boolean checkUser(Course course, User user);
}
