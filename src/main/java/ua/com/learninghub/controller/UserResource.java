package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.model.dao.UserCategoryDao;
import ua.com.learninghub.model.dao.UserDao;
import ua.com.learninghub.model.entities.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    UserDao userDao = new UserDao();

    @POST
    @Path("/login")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response checkUser(JSONObject obj) throws JSONException {
        User user = userDao.findByLoginPass(obj.getString("login"), obj.getString("password"));
        return user == null ? Response.status(401).build() : Response.status(200).build();
    }


    @POST
    @Path("/addUser")
    @Consumes({ MediaType.APPLICATION_JSON})
    public Response addUser(User user) throws JSONException {
        user.setCategory((new UserCategoryDao()).selectById(2));
        if (isNotEmail(user)) {
            return Response.status(400).build();
        }

        if(!userDao.insert(user))
            return Response.status(401).build();

        System.out.println(user);
        return Response.status(200).build();
    }

    private boolean isNotEmail(User user) {
        return !user.getEmail().matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    }

}