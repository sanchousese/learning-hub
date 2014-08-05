package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.Comment;
import java.util.List;

/**
 * Created by Max on 05.08.2014.
 */
public interface CommentDao {
    List<Comment> selectAll();

    Comment selectById(int id);

    boolean update(Comment comment);

    boolean insert(Comment comment);
}
