package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.Module;

import java.util.List;

/**
 * Created by vasax32 on 24.07.14.
 */
public interface ModuleDao {
    List<Module> selectAll();

    Module selectById(int id);

    boolean update(Module module);

    boolean insert(Module module);
}
