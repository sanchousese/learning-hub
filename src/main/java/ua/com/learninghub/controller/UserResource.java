package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.model.dao.implementation.UserCategoryDaoImpl;
import ua.com.learninghub.model.dao.implementation.UserDaoImpl;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserResource {

    UserDaoImpl userDaoImpl = new UserDaoImpl();

    @POST
    @Path("/login")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response checkUser(JSONObject obj) throws JSONException {
        User user = userDaoImpl.findByLoginPass(obj.getString("login"), obj.getString("password"));
        return user == null ? Response.status(401).build() : Response.status(200).build();
    }


    @POST
    @Path("/addUser")
    @Consumes({ MediaType.APPLICATION_JSON})
    public Response addUser(User user) throws JSONException {
        user.setCategory((new UserCategoryDaoImpl()).selectById(2));
        if (isNotEmail(user)) {
            return Response.status(400).build();
        }

        if(!userDaoImpl.insert(user))
            return Response.status(401).build();

        System.out.println(user);
        return Response.status(200).build();
    }

    private boolean isNotEmail(User user) {
        return !user.getEmail().matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public User getUser(@PathParam("userId") String userId) {
        User anUser = userDaoImpl.selectById(new Integer(userId));
        return anUser;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/courses")
    public List<Course> getUserCourses(@PathParam("userId") String userId) {
        User anUser = userDaoImpl.selectById(new Integer(userId));
        List<Course> userCourses = anUser.getCourses();
        return userCourses;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        List<User> users = userDaoImpl.selectAll();
        return users;
    }
}