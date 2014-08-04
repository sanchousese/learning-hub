package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Lesson;

import java.util.List;

/**
 * Created by vasax32 on 24.07.14.
 */
public interface LessonDao {
    List<Lesson> selectAll();

    Lesson selectById(int id);

    boolean update(Lesson lesson);

    boolean insert(Lesson lesson);
}
