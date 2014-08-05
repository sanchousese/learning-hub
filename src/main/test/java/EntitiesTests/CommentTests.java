package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.implementation.CommentDaoImpl;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CommentDao;
import ua.com.learninghub.model.entities.Comment;
import ua.com.learninghub.model.entities.Course;

import java.util.List;

/**
 * Created by Max on 05.08.2014.
 */
public class CommentTests {
    private CommentDao commentDao = new CommentDaoImpl();

/*

    @org.junit.Test
    public void insert(){
        Comment comment = new Comment();
        comment.setBody("COMMENT TEST");
        comment.setCourse((new CourseDaoImpl()).selectById(1));
        commentDao.insert(comment);
    }


*/
/*

    @org.junit.Test
    public void update(){
        Comment comment = commentDao.selectById(1);
        comment.setBody("Comment UPDATE");
        comment.setCourse((new CourseDaoImpl()).selectById(2));
        //test.setModule((new ModuleDaoImpl()).selectById(2));
        commentDao.update(comment);
    }
*//*



    @org.junit.Test
    public void selectAll(){
        List<Comment> comments = commentDao.selectAll();
        for (Comment comment : comments)
            System.out.println(comment);
    }

*/


}
