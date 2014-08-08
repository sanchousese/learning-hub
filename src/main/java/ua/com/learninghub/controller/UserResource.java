package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.util.UserLogic;
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
        String md5Pass = UserLogic.encryptPass(user.getPass());
        user.setPass(md5Pass);
        user.setCategory( new UserCategoryDaoImpl().selectById(4) );
        if (userDao.insert(user)) {
            return Response.status(200).build();
        } else return Response.status(401).build();
    }
    
    //need to manage this
    //@RolesAllowed({"Student", "Moderator", "Teacher"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public User getUser(@PathParam("userId") String userId) {
        User anUser = userDao.selectById(new Integer(userId));
        return anUser;
    }

    //@RolesAllowed({"Student", "Moderator", "Teacher"})
    @GET
    @Path("addCourse/{courseId}")
    public Response addCourseToUser(@Context HttpServletRequest hsr, @PathParam("courseId") int courseId) {
        String sessionId = cookieUtil.getSessionIdFromRequest(hsr);
        User user  = sessionDaoImpl.selectBySessionId(sessionId).getUser();
        //System.out.println(user);
        CourseDao courseDao = new CourseDaoImpl();
        Course course = courseDao.selectById(courseId);
        if(sessionId != null && (user = sessionDaoImpl.selectBySessionId(sessionId).getUser()) != null && courseDao.addUser(course, user)){
            return Response.ok().build();
        }
        else
            return Response.status(403).build();
    }

    //@RolesAllowed({"Student", "Moderator", "Teacher"})
    @GET
    @Path("/removeCourse")
    public  Response removeCourseFromUser(@Context HttpServletRequest hsr, @QueryParam("idCourse") int courseId) {
        String sessionId = cookieUtil.getSessionIdFromRequest(hsr);
        User user  = sessionDaoImpl.selectBySessionId(sessionId).getUser();
        CourseDao courseDao = new CourseDaoImpl();
        Course course = courseDao.selectById(courseId);

        //System.out.println(courseId);
        //System.out.println(course);

        //System.out.println(user);

        if(sessionId != null && (user = sessionDaoImpl.selectBySessionId(sessionId).getUser()) != null && courseDao.removeUser(course, user)){
            return Response.ok().build();
        }
        else
            return Response.status(403).build();
    }


    //@RolesAllowed({"Moderator", "Teacher"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("courses")
    public Response getUserCourses(@Context HttpServletRequest hsr) {
        String sessionId = cookieUtil.getSessionIdFromRequest(hsr);
        User user;
        if(sessionId != null && (user = sessionDaoImpl.selectBySessionId(sessionId).getUser()) != null){
            List<Course> userCourses = user.getCourses();
            return Response.ok(userCourses).build();
        }

        else
            return Response.status(403).build();
    }

    //@RolesAllowed({"Moderator", "Teacher"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        List<User> users = userDao.selectAll();
        return users;
    }

//    @GET
//    @Path("verifyCourse/{courseId}")
//    public Response verifyCourse(@Context HttpServletRequest hsr, @PathParam("courseId") int courseId) {
//        String sessionId = cookieUtil.getSessionIdFromRequest(hsr);
//        if(sessionId == null) return Response.status(403).build();
//        User user  = sessionDaoImpl.selectBySessionId(sessionId).getUser();
//        CourseDao courseDao = new CourseDaoImpl();
//        Course course = courseDao.selectById(courseId);
//        if(sessionId != null && (user = sessionDaoImpl.selectBySessionId(sessionId).getUser()) != null && courseDao.checkUser(course, user)){
//            return Response.ok().build();
//        }
//        else
//            return Response.status(403).build();
//    }
}