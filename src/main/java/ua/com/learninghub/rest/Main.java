package ua.com.learninghub.rest;

import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
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
    public Student[] getJson() {
        Student[] students = new Student[10];

        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i, i+1);
        }

        return students;
    }
}