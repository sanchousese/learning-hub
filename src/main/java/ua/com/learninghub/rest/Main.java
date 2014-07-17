package ua.com.learninghub.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
public class Main {

    @GET
    @Path("/sayHello/{userName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello(@PathParam("userName") String name) {
        return "Hello, " + name + "!";
    }

    @GET
    @Path("/getJson")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getJson() {
        Student student = new Student(1, 2, "Lobod", "root", "Lol");
        return student;

    }
}