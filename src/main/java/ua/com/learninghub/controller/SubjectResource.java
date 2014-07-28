package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.SubjectDaoImpl;
import ua.com.learninghub.model.dao.interfaces.SubjectDao;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.Subject;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Max on 28.07.2014.
 */

@Path("/subjects")
public class SubjectResource {
    private SubjectDao subjectDao = new SubjectDaoImpl();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSubjects(){
        List<Subject> subjects = subjectDao.selectAll();

        if (subjects == null || subjects.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Subject>>(subjects) {
        }).build();
    }

    @POST
    @Path("/subject")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Subject createSubject(Subject subject) {

        subjectDao.insert(subject);
        return subject;
    }
}
