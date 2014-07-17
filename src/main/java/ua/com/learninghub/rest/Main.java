package ua.com.learninghub.rest;
import ua.com.learninghub.database.entities.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    }*/

    @GET
    @Path("/getJson")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getJson() {
        Student student = new Student(1, 2, "Lobod", "root", "Lol");
        return student;

    }

    public class Student {
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
    }
}