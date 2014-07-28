package ua.com.learninghub.controller;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import ua.com.learninghub.controller.auth.SessionIdentifierGenerator;
import ua.com.learninghub.model.dao.FileSystemUtil;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.implementation.DisciplineDaoImpl;
import ua.com.learninghub.model.dao.implementation.SpecialtyDaoImpl;
import ua.com.learninghub.model.dao.implementation.SubjectDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.dao.interfaces.DisciplineDao;
import ua.com.learninghub.model.dao.interfaces.SpecialtyDao;
import ua.com.learninghub.model.dao.interfaces.SubjectDao;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.Specialty;
import ua.com.learninghub.model.entities.Subject;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Max on 18.07.2014.
 */
@PermitAll
@Path("/courses") // ...8080/rest/courses/
public class CourseResource {
    private CourseDao courseDao = new CourseDaoImpl();
    private SpecialtyDao specialtyDao = new SpecialtyDaoImpl();
    private DisciplineDao disciplineDao = new DisciplineDaoImpl();
    private SubjectDao subjectDao = new SubjectDaoImpl();
    private SessionIdentifierGenerator sessionIdentifierGenerator = new SessionIdentifierGenerator();

    //@RolesAllowed({"Moderator", "Teacher"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourses(){
        List<Course> courses = courseDao.selectAll();

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>>(courses) {
        }).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info/{courseId}") // ...8080/rest/courses/1234
    public Course getCourse(@PathParam("courseId") String courseId) {
        //courseDao.selectById((new Integer(courseId)).intValue()).getUsers().size();
        return courseDao.selectById((new Integer(courseId)).intValue());
    }

    @GET
    @Produces("text/plain")
    @Path("/info/{courseId}/numberOfPeople") // ...8080/rest/courses/1234
    public String getNumberOfPeopleCourse(@PathParam("courseId") String courseId) {
        return String.valueOf(courseDao.selectById((new Integer(courseId)).intValue()).getUsers().size());
    }


    @GET
    @Path("getLogoImage/{courseId}")
    @Produces("image/*")
    public Response getImage(@PathParam("courseId") int courseId) throws FileNotFoundException {
        Course course = courseDao.selectById(courseId);
        String filename  = null;
        if(course != null) filename= course.getMainImagePath();
        File f = FileSystemUtil.getCourseLogoByFilename(filename);
        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
    }

    //@RolesAllowed({"Moderator", "Teacher"})
    @POST
    @Path("/create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response create(
            @FormDataParam("name") String name,
            @FormDataParam("description") String description,
            @FormDataParam("price") int price,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        String filename = contentDispositionHeader.getFileName();
        if(filename != null) {
            String extension = filename.substring(filename.lastIndexOf('.') + 1);
            filename = sessionIdentifierGenerator.nextSessionId() + "." + extension;
        }

        System.out.println(filename);

        Course course = new Course(name, description, price, filename);
        course.setSubject(subjectDao.selectById(1));
        if(courseDao.insert(course)) {
            if(filename != null ) FileSystemUtil.writeCourseLogo(fileInputStream, filename);
            return Response.status(200).build();
        } else return Response.status(401).build();
    }


    @GET
    @Path("/getVideoStream")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getVideoStream(){
        File file = FileSystemUtil.getVideo();
        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM).build();
    }
/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{courseId}/user") // ...8080/rest/courses/1234
    public User getCourseUser(@PathParam("courseId") String courseId) {
        return courseDao.selectById((new Integer(courseId)).intValue());
    }
*/





    /* @DELETE
    @Path("{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete (@PathParam ("courseId") int courseId) {
        System.out.println(courseId);

        CourseDao.delete(courseId);

        return Response.ok().build();
    }*/

    /*@PUT
    @Path("{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(Course course) {

        System.out.println(course.getIdCourse());

        course = courseDao.update(course);

        return Response.ok().entity(course).build();

    }


    @POST
    @Path("course")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Course createCourse(Course course) {

        System.out.println(course.getDescription());
        System.out.println(course.getEndDate());

        courseDao.insert(course);

        return course;
    }

    @POST
    @Path("course")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Course createActivityParams(MultivaluedMap<String, String> formParams) {

        System.out.println(formParams.getFirst("description"));
        System.out.println(formParams.getFirst("price"));

        Course course = new Course();
        course.setDescription(formParams.getFirst("description"));
        course.setPrice(Integer.parseInt(formParams.getFirst("price")));

        courseDao.insert(course);

        return course;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{courseId}")
    public Response getActivity(@PathParam ("courseId") String courseId) {
        if(courseId == null || courseId.length() < 4) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Course course = courseDao.selectById(new Integer(courseId));

        if(course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(course).build();
    }

*/

}
