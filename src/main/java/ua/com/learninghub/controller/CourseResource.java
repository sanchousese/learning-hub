package ua.com.learninghub.controller;

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
@Path("/course") // ...8080/rest/courses/
public class CourseResource {
    private CourseDao courseDao = new CourseDaoImpl();
    private SpecialtyDao specialtyDao = new SpecialtyDaoImpl();
    private DisciplineDao disciplineDao = new DisciplineDaoImpl();
    private SubjectDao subjectDao = new SubjectDaoImpl();

    //@RolesAllowed({"Moderator", "Teacher"})
    @POST
    @Path("/getSpecialty")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialty(){
        ArrayList<Specialty> specialties = new ArrayList<Specialty>(specialtyDao.selectAll());
        if(specialties == null){
            return Response.status(Response.Status.GONE).build();
        }else return Response.ok(specialties).build();
    }

    @POST
    @Path("/getDiscipline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscipline(){
        ArrayList<Discipline> disciplines = new ArrayList<Discipline>(disciplineDao.selectAll());
        if(disciplines == null){
            return Response.status(Response.Status.GONE).build();
        }else return Response.ok(disciplines).build();
    }

    @POST
    @Path("/getSubject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubject(){
        ArrayList<Subject> subjects = new ArrayList<Subject>(subjectDao.selectAll());
        if(subjects == null){
            return Response.status(Response.Status.GONE).build();
        }else return Response.ok(subjects).build();
    }

    //@RolesAllowed({"Moderator", "Teacher"})
    @POST
    @Path("/create") // // ...8080/rest/courses/course
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCourse(Course course) {
            course.setSubject(subjectDao.selectById(1));
            System.out.println("createCourse");
            if (courseDao.insert(course)) {
            System.out.println("Ok");
            return Response.ok().build();
        } else {
            System.out.println("Bad");
            return Response.status(401).build();
        }
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
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses() {
        return courseDao.selectAll();
    }

    @GET
    @Path("getLogoImage/{courseId}")
    @Produces("image/*")
    public Response getImage(@PathParam("courseId") int courseId) throws FileNotFoundException {
        String filename = courseDao.selectById(courseId).getMainImagePath();
        File f = FileSystemUtil.getCourseLogoByFilename(filename);
        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
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
