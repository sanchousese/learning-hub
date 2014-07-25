package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.Discipline;

import java.util.List;

/**
 * Created by vasax32 on 24.07.14.
 */
public interface DisciplineDao {
    List<Discipline> selectAll();

    Discipline selectById(int id);

    boolean update(Discipline Discipline);

    boolean insert(Discipline discipline);
}
