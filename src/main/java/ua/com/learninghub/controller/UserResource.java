package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.controller.auth.CookieUtil;
import ua.com.learninghub.model.dao.implementation.SessionDaoImpl;
import ua.com.learninghub.model.dao.implementation.UserCategoryDaoImpl;
import ua.com.learninghub.model.dao.implementation.UserDaoImpl;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import ua.com.learninghub.model.dao.interfaces.UserDao;

import java.util.List;

@PermitAll
@Path("/user")
public class UserResource {

    private UserDao userDao = new UserDaoImpl();
    private SessionDaoImpl sessionDaoImpl = new SessionDaoImpl();
    private CookieUtil cookieUtil = new CookieUtil();

    @POST
    @Path("/addUser")
    @Consumes({ MediaType.APPLICATION_JSON})
    public Response addUser(User user) {
        user.setCategory( new UserCategoryDaoImpl().selectById(4) );
        if (userDao.insert(user)) {
            return Response.status(200).build();
        } else return Response.status(401).build();
    }

    @RolesAllowed({"Moderator", "Teacher", "Student"})
    @POST
    @Path("/userInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userInfo(@Context HttpServletRequest hsr) {
        String sessionId = cookieUtil.getSessionIdFromRequest(hsr);
        User user;
        if(sessionId != null && (user = sessionDaoImpl.selectBySessionId(sessionId).getUser()) != null)
            return Response.ok(user).build();
        else
            return Response.status(403).build();
    }

    @POST
    @Path("/login")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response loginAuth(@Context HttpServletRequest hsr, @Context HttpServletResponse rspn, JSONObject obj)
            throws JSONException {
        User user = userDao.findByLoginPass(obj.getString("login"), obj.getString("password"));
        if(user != null && cookieUtil.insertSessionUID(rspn, user))
            return Response.status(200).build();
        else
            return Response.status(401).build();
    }

    @RolesAllowed({"Moderator", "Teacher", "Student"})
    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoutAuth(@Context HttpServletRequest hsr, @Context HttpServletResponse rspn) {
        if(cookieUtil.removeSessionUID(hsr, rspn))
            return Response.ok().build();
        else
            return Response.status(404).build();
    }
    
    //need to manage this
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public User getUser(@PathParam("userId") String userId) {
        User anUser = userDao.selectById(new Integer(userId));
        return anUser;
    }

    @RolesAllowed({"Moderator", "Teacher"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/courses")
    public List<Course> getUserCourses(@PathParam("userId") String userId) {
        User anUser = userDao.selectById(new Integer(userId));
        List<Course> userCourses = anUser.getCourses();
        return userCourses;
    }

    @RolesAllowed({"Moderator", "Teacher"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        List<User> users = userDao.selectAll();
        return users;
    }
}