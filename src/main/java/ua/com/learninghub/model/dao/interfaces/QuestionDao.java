package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Question;
import ua.com.learninghub.model.entities.Test;

import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
public interface QuestionDao {
    List<ua.com.learninghub.model.entities.Question> selectAll();

    ua.com.learninghub.model.entities.Question selectById(int id);

    boolean update(Question question);

    boolean insert(Question question);
}
