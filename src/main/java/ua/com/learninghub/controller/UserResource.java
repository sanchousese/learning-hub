package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.model.dao.UserCategoryDao;
import ua.com.learninghub.model.dao.UserDao;
import ua.com.learninghub.model.entities.User;
import ua.com.learninghub.model.entities.UserCategory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserResource {

        UserDao userDao = new UserDao();

        @POST
        @Path("/login")
        @Consumes({ MediaType.APPLICATION_JSON})
        public Response checkUser(JSONObject obj) throws JSONException {
            User user = userDao.findByLoginPass(obj.getString("login"), obj.getString("password"));
            return user == null ? Response.status(401).build() : Response.status(200).build();
        }


    @POST
    @Path("/addUser")
    @Consumes({ MediaType.APPLICATION_JSON})
    public Response addUser(User user) throws JSONException {
        user.setCategory((new UserCategoryDao()).selectById(2));
        userDao.insert(user);
        System.out.println(user);
        return Response.status(200).build();
    }

}