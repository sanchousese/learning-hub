package ua.com.learninghub.controller;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.controller.auth.CookieUtil;
import ua.com.learninghub.controller.auth.SessionIdentifierGenerator;
import ua.com.learninghub.model.dao.FileSystemUtil;
import ua.com.learninghub.model.dao.implementation.*;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.dao.interfaces.DisciplineDao;
import ua.com.learninghub.model.dao.interfaces.SpecialtyDao;
import ua.com.learninghub.model.dao.interfaces.SubjectDao;
import ua.com.learninghub.model.entities.*;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
    private SessionIdentifierGenerator sessionIdentifierGenerator = new SessionIdentifierGenerator();
    private SessionDaoImpl sessionDaoImpl = new SessionDaoImpl();
    private CookieUtil cookieUtil = new CookieUtil();

    @GET
    @Path("/getSpecialty")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialty(){
        ArrayList<Specialty> specialties = new ArrayList<Specialty>(specialtyDao.selectAll());
        if(specialties == null){
            return Response.status(Response.Status.GONE).build();
        }else return Response.ok(specialties).build();
    }

    @GET
    @Path("/getDiscipline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscipline(){
        ArrayList<Discipline> disciplines = new ArrayList<Discipline>(disciplineDao.selectAll());
        if(disciplines == null){
            return Response.status(Response.Status.GONE).build();
        }else return Response.ok(disciplines).build();
    }

    @GET
    @Path("/getSubject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubject(){
        ArrayList<Subject> subjects = new ArrayList<Subject>(subjectDao.selectAll());
        if(subjects == null){
            return Response.status(Response.Status.GONE).build();
        }else return Response.ok(subjects).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info/{courseId}") // ...8080/rest/courses/1234
    public Course getCourse(@PathParam("courseId") int courseId) {

        return courseDao.selectById(courseId);
    }

    @GET
    @Path("/getVideoCourse/{courseId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getVideoCourse(@PathParam("courseId") int courseId){
        File file = null;
        try {
            file = FileSystemUtil.getVideoCourse(courseId);
        } catch (Exception e) {
            Response.status(401).build();
        }
        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM).build();
    }

    @GET
    @Produces("text/plain")
    @Path("/info/{courseId}/numberOfPeople") // ...8080/rest/courses/1234
    public String getNumberOfPeopleCourse(@PathParam("courseId") int courseId) {
        return String.valueOf(courseDao.selectById(courseId).getUsers().size());
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
        Course course = courseDao.selectById(courseId);
        String filename  = null;
        if(course != null) filename= course.getMainImagePath();
        File f = FileSystemUtil.getCourseLogoByFilename(courseId, filename);
        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
    }

    @RolesAllowed({"Moderator", "Teacher"})
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(JSONObject json) throws JSONException {
        //need to manage this code
        Course course = new Course();
        course.setName((String)json.get("name"));
        course.setDescription((String)json.get("description"));
        course.setPrice(new Integer((String)json.get("price")));
        course.setSubject(subjectDao.selectById((Integer)json.get("subject")));
        if(courseDao.insert(course)) {
            return Response.ok(new String(Integer.toString(course.getIdCourse()))).build();
        } else return Response.status(401).build();
    }

    @RolesAllowed({"Moderator", "Teacher"})
    @POST
    @Path("/uploadMainLogo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadMainLogo(
            @FormDataParam("courseId") String courseId,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        String filename = contentDispositionHeader.getFileName();
        if(filename != null || filename != "") {
            String extension = filename.substring(filename.lastIndexOf('.') + 1);
            System.out.println(extension);
            if(!(extension.equals("bmp") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("wbmp") || extension.equals("png") || extension.equals("gif"))) {
                System.out.println("Incorrect extension");
                return Response.status(401).build();

            }
            filename = sessionIdentifierGenerator.nextSessionId() + "." + extension;
        }

        Course course = courseDao.selectById(new Integer(courseId));
        //if not null
        String oldFilename = course.getMainImagePath();
        if(filename != null || filename != "") {
            course.setMainImagePath(filename);
            if (courseDao.update(course)) {
                if (oldFilename != null || oldFilename != "")
                    FileSystemUtil.clearCourseLogo(oldFilename, new Integer(courseId));
                FileSystemUtil.writeCourseLogo(fileInputStream, filename, new Integer(courseId));
            } else return Response.status(401).build();
        }
        return Response.status(200).build();
    }

    @RolesAllowed({"Moderator", "Teacher"})
    @POST
    @Path("/uploadMainIntro")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadMainIntro(
            @FormDataParam("courseId") String courseId,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        String filename = contentDispositionHeader.getFileName();
        if(filename != null) {
            String extension = filename.substring(filename.lastIndexOf('.') + 1);
            if(!extension.equals("mp4")) return Response.status(401).build();
            filename = sessionIdentifierGenerator.nextSessionId() + "." + extension;
        }

        Course course = courseDao.selectById(new Integer(courseId));
        //if not null
        String oldFilename = course.getMainVideoPath();
        if(filename != null || filename != "") {
            course.setMainVideoPath(filename);
            if (courseDao.update(course)) {
                if (oldFilename != null || oldFilename != "")
                    FileSystemUtil.clearMainIntro(oldFilename, new Integer(courseId));
                FileSystemUtil.writeMainIntro(fileInputStream, filename, new Integer(courseId));
            } else return Response.status(401).build();
        }
        return Response.status(200).build();
    }


    @GET
    @Path("/getVideoStream")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getVideoStream(){
        File file = FileSystemUtil.getVideo();
        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM).build();
    }

    @GET
    @Path("verifyCourse/{courseId}")
    public Response verifyCourse(@Context HttpServletRequest hsr, @PathParam("courseId") int courseId) {
        String sessionId = cookieUtil.getSessionIdFromRequest(hsr);
        User user  = sessionDaoImpl.selectBySessionId(sessionId).getUser();
        CourseDao courseDao = new CourseDaoImpl();
        Course course = courseDao.selectById(courseId);
        if(sessionId != null && (user = sessionDaoImpl.selectBySessionId(sessionId).getUser()) != null && courseDao.checkUser(course, user)){
            return Response.ok().build();
        }
        else
            return Response.status(403).build();
    }

    @RolesAllowed({"Moderator", "Teacher"})
    @PUT
    @Path("/put")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(JSONObject json) throws JSONException {
        //need to manage this code
        Course course = new Course();
        course.setIdCourse(new Integer((String)json.get("idCourse")));
        course.setName((String)json.get("name"));
        course.setDescription((String)json.get("description"));
        course.setPrice(new Integer((String)json.get("price")));
        course.setSubject(subjectDao.selectById((Integer)json.get("subject")));
        course.setRate(courseDao.selectById(course.getIdCourse()).getRate());
        course.setMainImagePath(courseDao.selectById(course.getIdCourse()).getMainImagePath());
        course.setMainVideoPath(courseDao.selectById(course.getIdCourse()).getMainVideoPath());
        if(courseDao.update(course)) {
            return Response.ok(new String(Integer.toString(course.getIdCourse()))).build();
        } else return Response.status(401).build();
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

/*
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
