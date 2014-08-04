package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Answer;
import ua.com.learninghub.model.entities.Test;

import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
public interface AnswerDao {
    List<Answer> selectAll();

    Answer selectById(int id);

    boolean update(Answer answer);

    boolean insert(Answer answer);
}
