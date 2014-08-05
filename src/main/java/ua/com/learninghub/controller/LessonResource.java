package ua.com.learninghub.controller;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.controller.auth.SessionIdentifierGenerator;
import ua.com.learninghub.model.dao.FileSystemUtil;
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
import java.io.File;
import java.io.InputStream;
import java.util.List;
// * Created by Max on 04.08.2014.

@PermitAll
@Path("/lessons") // ...8080/rest/courses/
public class LessonResource {
    CourseDao courseDao = new CourseDaoImpl();
    LessonDao lessonDao = new LessonDaoImpl();
    SessionIdentifierGenerator sessionIdentifierGenerator = new SessionIdentifierGenerator();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLessons(@QueryParam(value = "idCourse") int courseID) {

        System.out.println("CID:" + courseID);

        Course course = courseDao.selectById(courseID);

        System.out.println("CRS:" + course);

        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Lesson> lessons = course.getLessons();

        for (Lesson l : lessons)
        System.out.println("ls:" + l);

        if (lessons == null || lessons.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Lesson>>(lessons) {
        }).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lesson/{idLesson}") // ...8080/rest/courses/1234
    public Response getCourse(@PathParam("idLesson") int lessonID) {
        Lesson lesson = lessonDao.selectById(lessonID);

        if (lesson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().entity(lesson).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(JSONObject json) throws JSONException {
        //need to manage this code
        Lesson lesson = new Lesson();
        lesson.setName((String)json.get("name"));
        lesson.setDescription((String)json.get("description"));
        lesson.setCourse(courseDao.selectById(new Integer((String)json.get("course"))));
        if(lessonDao.insert(lesson)) {
            return Response.ok(new String(Integer.toString(lesson.getIdLesson()))).build();
        } else return Response.status(401).build();
    }

    @POST
    @Path("/uploadVideo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadMainIntro(
            @FormDataParam("lessonId") String lessonId,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        String filename = contentDispositionHeader.getFileName();
        System.out.println("File f: " + filename);
        if(filename != null) {
            System.out.println("File found.");
            String extension = filename.substring(filename.lastIndexOf('.') + 1);
            filename = sessionIdentifierGenerator.nextSessionId() + "." + extension;
        }

        Lesson lesson = lessonDao.selectById(new Integer(lessonId));
        //if not null
        lesson.setLessonVideo(filename);
        if(lessonDao.update(lesson)) {
            if(filename != null ) FileSystemUtil.writeLessonVideo(fileInputStream, filename, new Integer(lessonId));
            return Response.status(200).build();
        } else return Response.status(401).build();
    }

    @GET
    @Path("/getLessonVideo/{lessonId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getVideoCourse(@PathParam("lessonId") int lessonId){
        File file = null;
        try {
            file = FileSystemUtil.getLessonVideo(lessonId);
        } catch (Exception e) {
            Response.status(401).build();
        }
        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM).build();
    }

}




