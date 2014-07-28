package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.DisciplineDaoImpl;
import ua.com.learninghub.model.dao.interfaces.DisciplineDao;
import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.Specialty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 28.07.2014.
 */

@Path("/disciplines")
public class DisciplineResource {
    DisciplineDao disciplineDao = new DisciplineDaoImpl();



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDisciplines(){
        List<Discipline> disciplines = disciplineDao.selectAll();
        if(disciplines == null){
            return Response.status(Response.Status.GONE).build();
        }else return Response.ok(disciplines).build();
    }

    @POST
    @Path("/discipline")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Discipline createSpeciality(Discipline discipline) {

        disciplineDao.insert(discipline);
        return discipline;
    }


}
