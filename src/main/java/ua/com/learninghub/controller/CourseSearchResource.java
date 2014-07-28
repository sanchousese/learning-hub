package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.implementation.SpecialtyDaoImpl;
import ua.com.learninghub.model.dao.implementation.SubjectDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.dao.interfaces.SpecialtyDao;
import ua.com.learninghub.model.dao.interfaces.SubjectDao;
import ua.com.learninghub.model.entities.*;

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
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public CourseSearch test() {
        CourseSearch courseSearch = new CourseSearch();
        courseSearch.setIdSubject(1);
        courseSearch.setIdDiscipline(2);
        courseSearch.setIdSpeciality(3);
        courseSearch.setIdFrom(2);
        courseSearch.setIdTo(4);
        courseSearch.setKeywords("ababagalamaga");
        courseSearch.setSearchType("SEARCH_BY_RANGES");

        return courseSearch;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchForCourses(CourseSearch search) {
        /*CourseSearch courseSearch = new CourseSearch();
        courseSearch.setIdSubject(1);
        courseSearch.setIdDiscipline(2);
        courseSearch.setIdSpeciality(3);
        courseSearch.setIdFrom(1);
        courseSearch.setIdTo(3);
        courseSearch.setKeywords("ababagalamaga");
        courseSearch.setSearchType(CourseSearchType.SEARCH_BY_RANGES);
        CourseSearch search = courseSearch;
*/

        List<Course> courses = courseDao.findByConstraints(search);

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>> (courses) {}).build();
    }


    @GET
    @Path("/byDiscipline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByDiscipline(@QueryParam(value = "idDiscipline") int disID){

        List<Course> courses = courseDao.selectByDiscipline(disID);

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>>(courses) {
        }).build();
    }


    @GET
    @Path("/bySpeciality")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchBySpeciality(@QueryParam(value = "idSpeciality") int specID){

        List<Course> courses = courseDao.selectBySpeciality(specID);

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>>(courses) {
        }).build();
    }

    // returns list of courses by given subject
    @GET
    @Path("/bySubject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchBySubject(@QueryParam(value = "idSubject") int subjID){

        List<Course> courses = courseDao.selectBySubject(subjID);

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>>(courses) {
        }).build();
    }


    // for global search field
    // TODO: improve to list of keyword (we ce only search by one string now, it's bad)
    @GET
    @Path("/byKeywords")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(@QueryParam(value="keywords") String keywords) {
        System.out.println();

        List<Course> courses = courseDao.selectByName(keywords);

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>>(courses) {}).build();
    }


    // for "show more" button
    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByRange(@QueryParam(value= "idFrom") int idFrom,
                                     @QueryParam(value = "idTo") int idTo) {
        System.out.println();

        List<Course> courses = courseDao.selectById(idFrom, idTo);

        if (courses == null || courses.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Course>>(courses) {}).build();
    }

}
