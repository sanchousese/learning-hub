package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.model.dao.implementation.CommentLessonDaoImpl;
import ua.com.learninghub.model.dao.implementation.LessonDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CommentLessonDao;
import ua.com.learninghub.model.entities.Comment;
import ua.com.learninghub.model.entities.CommentLesson;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Max on 06.08.2014.
 */
@Path("/commentLesson")
public class CommentLessonResource {
    CommentLessonDao commentLessonDao = new CommentLessonDaoImpl();

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(JSONObject json) throws JSONException {
        //need to manage this code
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        CommentLesson comment = new CommentLesson();
        comment.setBody((String) json.get("body"));
        comment.setDate(new Timestamp(now.getTime()));
        comment.setLesson((new LessonDaoImpl()).selectById(new Integer(json.get("idLesson").toString())));

        if (commentLessonDao.insert(comment)) {
            return Response.ok(new String(Integer.toString(comment.getIdCommentLesson()))).build();
        } else return Response.status(401).build();
    }

    @GET
    @Path("/byLesson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCourse(@QueryParam(value = "idLesson") int lessonID) {
        List<CommentLesson> comments = (new LessonDaoImpl()).selectById(lessonID).getCommentsLesson();

        Collections.sort(comments, new DateComparatorLes());
        Collections.reverse(comments);


        if (comments == null || comments.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<CommentLesson>>(comments) {
        }).build();

    }
}


class DateComparatorLes implements Comparator<CommentLesson> {
    @Override
    public int compare(CommentLesson a, CommentLesson b) {
        return a.getDate().compareTo(b.getDate());
    }
}
