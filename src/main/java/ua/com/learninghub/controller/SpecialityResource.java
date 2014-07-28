package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.SpecialtyDaoImpl;
import ua.com.learninghub.model.dao.interfaces.SpecialtyDao;
import ua.com.learninghub.model.entities.Discipline;
import ua.com.learninghub.model.entities.Specialty;
import ua.com.learninghub.model.entities.Subject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 28.07.2014.
 */

@Path("/specialities")
public class SpecialityResource {
    SpecialtyDao specialtyDao = new SpecialtyDaoImpl();



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSpecialities(){
        List<Specialty> specialities = specialtyDao.selectAll();
        if (specialities == null){
            return Response.status(Response.Status.GONE).build();
        }else return Response.ok(specialities).build();
    }

    @POST
    @Path("/speciality")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Specialty createSpeciality(Specialty specialty) {

        specialtyDao.insert(specialty);
        return specialty;
    }

}
