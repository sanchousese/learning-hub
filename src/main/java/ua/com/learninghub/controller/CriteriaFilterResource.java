package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.DisciplineDaoImpl;
import ua.com.learninghub.model.dao.implementation.SpecialtyDaoImpl;
import ua.com.learninghub.model.dao.implementation.SubjectDaoImpl;
import ua.com.learninghub.model.dao.interfaces.DisciplineDao;
import ua.com.learninghub.model.dao.interfaces.SpecialtyDao;
import ua.com.learninghub.model.dao.interfaces.SubjectDao;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.Specialty;
import ua.com.learninghub.model.entities.Subject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Max on 28.07.2014.
 */
@Path("search/filter")
public class CriteriaFilterResource {

    SpecialtyDao specialtyDao = new SpecialtyDaoImpl();
    DisciplineDao disciplineDao = new DisciplineDaoImpl();
    SubjectDao subjectDao = new SubjectDaoImpl();

    // returns
    @GET
    @Path("/subjects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubjects(@QueryParam(value = "idDiscipline") int idDiscipline){
        Discipline discipline = disciplineDao.selectById(idDiscipline);

        if(discipline == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        List<Subject> subjects = discipline.getSubjects();


        if (subjects == null || subjects.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Subject>>(subjects) {
        }).build();
    }

    @GET
    @Path("/disciplines")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisciplines(@QueryParam(value = "idSpeciality") int idSpeciality){
        Specialty specialty = specialtyDao.selectById(idSpeciality);

        List<Discipline> disciplines = specialty.getDisciplines();


        if (disciplines == null || disciplines.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Discipline>>(disciplines) {
        }).build();
    }


    @GET
    @Path("/specialities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialities(){
        List<Specialty> specialties = specialtyDao.selectAll();

        if (specialties == null || specialties.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Specialty>>(specialties) {
        }).build();
    }
}

