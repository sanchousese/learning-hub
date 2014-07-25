package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.Specialty;

import java.util.List;

/**
 * Created by vasax32 on 25.07.14.
 */
public interface SpecialtyDao {
    List<Specialty> selectAll();

    Specialty selectById(int id);

    boolean update(Specialty specialty);

    boolean insert(Specialty specialty);
}
