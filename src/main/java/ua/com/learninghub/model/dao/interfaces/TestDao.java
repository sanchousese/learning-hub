package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Specialty;
import ua.com.learninghub.model.entities.Test;

import java.util.List;

/**
 * Created by vasax32 on 25.07.14.
 */
public interface TestDao {
    List<Test> selectAll();

    Test selectById(int id);

    boolean update(Test test);

    boolean insert(Test test);
}
