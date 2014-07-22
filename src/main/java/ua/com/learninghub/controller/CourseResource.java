package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.entities.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Max on 18.07.2014.
 */
@Path("courses") // ...8080/rest/courses/
public class CourseResource {
    private CourseDao courseDao = new CourseDaoImpl();//test

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses() {
        return courseDao.selectAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{courseId}") // ...8080/rest/courses/1234
    public Course getCourse(@PathParam("courseId") String courseId) {
        return courseDao.selectById((new Integer(courseId)).intValue());
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
