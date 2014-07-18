package ua.com.learninghub.rest;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class Main {

    List<Student> studentList;

    public void init() {
        studentList = new ArrayList<Student>();
        studentList.add(new Student(1, 2, "Lobod", "root", "Lol"));
        studentList.add(new Student(1, 2, "root", "root", "root"));
    }

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
    public Response sayPlainTextHello(JSONObject obj) throws JSONException {
        init();
        String login = obj.getString("login");
        String password = obj.getString("password");

        for (Student i : studentList)
            if(i.login.equals(login) &&  i.password.equals(password))
                return Response.status(200).build();


        return Response.status(401).build();
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