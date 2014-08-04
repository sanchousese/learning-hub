package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.implementation.LessonDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.dao.interfaces.LessonDao;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.Lesson;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
// * Created by Max on 04.08.2014.

@PermitAll
@Path("/lessons") // ...8080/rest/courses/
public class LessonResource {
    CourseDao courseDao = new CourseDaoImpl();
    LessonDao lessonDao = new LessonDaoImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLessons(@QueryParam(value = "idCourse") int courseID) {
        Course course = courseDao.selectById(courseID);

        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Lesson> lessons = course.getLessons();

        if (lessons == null || lessons.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Lesson>>(lessons) {
        }).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info/{idLesson}") // ...8080/rest/courses/1234
    public Response getCourse(@PathParam("idLesson") int lessonID) {
        Lesson lesson = lessonDao.selectById(lessonID);

        if (lesson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().entity(lesson).build();
        }
    }

}




