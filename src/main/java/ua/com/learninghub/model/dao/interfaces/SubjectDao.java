package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.Subject;

import java.util.List;

/**
 * Created by Max on 21.07.2014.
 */
public interface SubjectDao {


    List<Subject> selectAll();

    Subject selectById(int id);

    int update(Subject subject);

    void insert(Subject subject);
}
