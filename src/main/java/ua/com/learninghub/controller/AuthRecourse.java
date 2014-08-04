package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.controller.auth.CookieUtil;
import ua.com.learninghub.model.dao.implementation.SessionDaoImpl;
import ua.com.learninghub.model.dao.implementation.UserDaoImpl;
import ua.com.learninghub.model.dao.interfaces.UserDao;
import ua.com.learninghub.model.entities.User;
import ua.com.learninghub.util.UserLogic;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vasax32 on 01.08.14.
 */
//@PermitAll
@Path("/")
public class AuthRecourse {

    private UserDao userDao = new UserDaoImpl();
    private SessionDaoImpl sessionDaoImpl = new SessionDaoImpl();
    private CookieUtil cookieUtil = new CookieUtil();

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
        String md5Pass = UserLogic.encryptPass(obj.getString("password"));

        User user = userDao.findByLoginPass(obj.getString("login"), md5Pass);
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
}
