package ua.com.learninghub.rest;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.database.entities.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    public Student getJson() {
        Student student = new Student(1, 2, "Lobod", "root", "Lol");
        return student;

    }

    @POST
    @Path("/login")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Auth sayPlainTextHello(JSONObject obj) throws JSONException {
        return new Auth(obj.getString("login"), obj.getString("password"));
    }

    public class Auth{
        String login;
        String password;

        public Auth(String login, String password) {
            this.login = login;
            this.password = password;
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