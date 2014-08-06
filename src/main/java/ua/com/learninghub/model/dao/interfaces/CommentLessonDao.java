package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.CommentLesson;

import java.util.List;

/**
 * Created by Max on 06.08.2014.
 */
public interface CommentLessonDao {
    List<CommentLesson> selectAll();

    CommentLesson selectById(int id);

    boolean update(CommentLesson commentLesson);

    boolean insert(CommentLesson commentLesson);

}
