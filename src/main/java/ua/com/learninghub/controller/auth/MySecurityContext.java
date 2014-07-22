package ua.com.learninghub.controller.auth;

import ua.com.learninghub.model.entities.User;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Created by vasax32 on 21.07.14.
 */
public class MySecurityContext implements javax.ws.rs.core.SecurityContext {

    private final User user;
    private final Session session;

    public MySecurityContext(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isSecure() {
        return (null != session) ? session.isSecure() : false;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (null == session || !session.isActive()) {
            // Forbidden
            Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission Denied").build();
            throw new WebApplicationException(denied);
        }

        try {
            // this user has this role?
            //return user.getRoles().contains(User.Role.valueOf(role));
            return user.getCategory().getName().equals(role);
        } catch (Exception e) {
        }

        return false;
    }
}
