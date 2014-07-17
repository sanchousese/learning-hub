package ua.com.learninghub.rest;
import ua.com.learninghub.database.entities.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Path("/user/{userName}")
public class Main {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello(@PathParam("userName") String name) {
        return "Hello, " + name + "!";
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public User gketUser(){
        User user = new User();
        user.setLogin("Some login");
        return user;
    }
}