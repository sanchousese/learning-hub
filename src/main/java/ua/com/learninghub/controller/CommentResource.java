package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.model.dao.implementation.CommentDaoImpl;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CommentDao;
import ua.com.learninghub.model.entities.Comment;
import ua.com.learninghub.model.entities.CommentLesson;
import ua.com.learninghub.model.entities.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Path("/comment")
public class CommentResource {
    CommentDao commentDao = new CommentDaoImpl();

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(JSONObject json) throws JSONException {
        //need to manage this code
        Comment comment = new Comment();
        comment.setBody((String) json.get("body"));

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        comment.setDate(new Timestamp(now.getTime()));


        comment.setCourse((new CourseDaoImpl()).selectById((new Integer(json.get("idCourse").toString()))));

        if (commentDao.insert(comment)) {
            return Response.ok(new String(Integer.toString(comment.getIdComment()))).build();
        } else return Response.status(401).build();
    }

    @GET
    @Path("/byCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCourse(@QueryParam(value = "idCourse") int courseID) {
        List<Comment> comments = (new CourseDaoImpl()).selectById(courseID).getComments();

        Collections.sort(comments, new DateComparator());
        Collections.reverse(comments);

        if (comments == null || comments.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Comment>>(comments) {
        }).build();

    }
}

class DateComparator implements Comparator<Comment> {
    @Override
    public int compare(Comment a, Comment b) {
        return a.getDate().compareTo(b.getDate());
    }
}