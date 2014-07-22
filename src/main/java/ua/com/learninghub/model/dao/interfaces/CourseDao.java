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

    void insert(Course course);

    void delete(int courseId);
}
