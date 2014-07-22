package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.controller.auth.Session;
import ua.com.learninghub.controller.auth.SessionRepository;
import ua.com.learninghub.model.dao.SessionDao;
import ua.com.learninghub.model.dao.UserCategoryDao;
import ua.com.learninghub.model.dao.UserDao;
import ua.com.learninghub.model.entities.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@PermitAll
@Path("/user")
public class UserResource {

    private UserDao userDao = new UserDao();
    private SessionDao sessionDao = new SessionDao();

//    @POST
//    @Path("/login")
//    @Consumes({ MediaType.APPLICATION_JSON })
//    public Response checkUser(JSONObject obj) throws JSONException {
//        User user = userDao.findByLoginPass(obj.getString("login"), obj.getString("password"));
//        return user == null ? Response.status(401).build() : Response.status(200).build();
//    }


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

    @RolesAllowed({"Admin", "Moderator", "Student"})
    @POST
    @Path("/userInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userInfo(@Context HttpServletRequest hsr) {
        if(hsr == null) return Response.status(404).build();
        HttpSession session =  hsr.getSession(false);
        if(session != null){
            String sessionId = session.getId();
            User user = sessionDao.selectBySessionId(sessionId).getUser();
            return Response.ok(user).build();
        }else{
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response loginAuth(@Context HttpServletRequest hsr, JSONObject obj) throws JSONException {
        User user = userDao.findByLoginPass(obj.getString("login"), obj.getString("password"));
        if(user == null){
            return Response.status(401).build();
        } else {
            String sessionID = hsr.getSession().getId();
            boolean suc = sessionDao.insert(new Session(sessionID, user));
            if(!suc) return Response.status(401).build();
            return Response.status(200).build();
        }
    }

    @RolesAllowed({"Admin", "Moderator", "Student"})
    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoutAuth(@Context HttpServletRequest hsr) {
        HttpSession session =  hsr.getSession(false);
        if(session != null){
            String sessionId = session.getId();
            User user = sessionDao.selectBySessionId(sessionId).getUser();
            if(user != null){
                sessionDao.deleteBySessionId(sessionId);
                session.invalidate();
                return Response.ok().build();
            } else Response.status(404).build();
            return Response.ok(user).build();
        }else{
            return Response.status(404).build();
        }
    }
    
    //need to manage this
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