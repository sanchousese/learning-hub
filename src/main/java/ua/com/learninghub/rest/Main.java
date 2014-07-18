package ua.com.learninghub.rest;
import com.sun.jersey.api.core.HttpRequestContext;
import ua.com.learninghub.rest.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class Main {
    /*@GET
    @Path("/{userName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello(@PathParam("userName") String name) {
        return "Hello, " + name + "!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    public User gketUser(){
        User user = new User();
        user.setLogin("Some login");
        return user;
    }*///

    @GET
    @Path("/getJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //Student student = new Student(1, 2, "Lobod", "root", "Lol");
        return "SomeString";

    }

    @POST
    @Path("/postUser")
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponse postUser(User user) {
        System.out.println(user);
        //return Response.status(Response.Status.OK).build();

        //return Response.ok(new UserResponse("Проверка utf-8", "1.jpg")).status(200).build();
        return new UserResponse("SomeDescription", "1.jpg");
    }

    /*public class Student {
        int id;
        int idCategory;
        String login;
        String password;
        String email;

        public Student(int id, int idCategory, String login, String password, String email) {
            this.id = id;
            this.idCategory = idCategory;
            this.login = login;
            this.password = password;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdCategory() {
            return idCategory;
        }

        public void setIdCategory(int idCategory) {
            this.idCategory = idCategory;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }*/
}