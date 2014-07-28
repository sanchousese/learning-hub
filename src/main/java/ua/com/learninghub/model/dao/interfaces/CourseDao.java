package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.CourseSearch;
import ua.com.learninghub.model.entities.Subject;

import java.util.List;

/**
 * Created by Max on 21.07.2014.
 */
public interface CourseDao {

    List<Course> selectBySubject(int subjID);

    List<Course> selectAll();

    Course selectById(int id);

    Course update(Course course);

    boolean insert(Course course);

    void delete(int courseId);

    List<Course> selectByName(String name);

    List<Course> selectById(int idFrom, int idTo);


    List<Course> selectBySpeciality(int specID);

    List<Course> selectByDiscipline(int disID);

    List<Course> findByConstraints(CourseSearch search);
}
