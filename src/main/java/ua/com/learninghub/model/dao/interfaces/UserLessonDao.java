package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Answer;
import ua.com.learninghub.model.entities.UserLesson;

import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
public interface UserLessonDao {
    List<UserLesson> selectAll();

    UserLesson selectById(int id);

    boolean update(UserLesson userLesson);

    boolean insert(UserLesson userLesson);

    public boolean selectByUserIdLessonId(int lessonId, int userId);
}
