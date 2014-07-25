package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Course;

import java.util.List;

/**
 * Created by Max on 21.07.2014.
 */
public interface CourseDao {
    List<Course> selectAll();

    Course selectById(int id);

    Course update(Course course);

    boolean insert(Course course);

    void delete(int courseId);

    List<Course> selectByName(String name);

    List<Course> selectById(int idFrom, int idTo);
}
