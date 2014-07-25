package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.entities.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Max on 18.07.2014.
 */
@Path("search/courses")
public class CourseSearchResource {

    private CourseDao courseDao = new CourseDaoImpl();

    @GET
    @Path("/byName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchForCourses(@QueryParam(value="name") String name) {
        System.out.println();

        List<Course> courses = courseDao.selectByName(name);

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>>(courses) {}).build();
    }


    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchForCourses(@QueryParam(value= "idFrom") int idFrom,
                                     @QueryParam(value = "idTo") int idTo) {
        System.out.println();

        List<Course> courses = courseDao.selectById(idFrom, idTo);

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>>(courses) {}).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses() {
        return courseDao.selectAll();
    }


}
