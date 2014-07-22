package ua.com.learninghub.controller.auth;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import ua.com.learninghub.model.dao.implementation.SessionDaoImpl;
import ua.com.learninghub.model.entities.Session;
import ua.com.learninghub.model.entities.User;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;

/**
 * Created by vasax32 on 21.07.14.
 */
@Provider    // register as jersey's provider
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter {

    private SessionDaoImpl sessionDaoImpl = new SessionDaoImpl();

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        User user = null;
        Session session = null;

        // Get session id from request header
        java.util.Map<String, Cookie> cookies =  request.getCookies();

        if(cookies != null) {

            Cookie sessionCookie = cookies.get("JSESSIONID");

            if(sessionCookie != null) {

                final String sessionId = sessionCookie.getValue();

                if (sessionId != null && sessionId.length() > 0) {
                    // Load session object from repository
                    session = sessionDaoImpl.selectBySessionId(sessionId);

                    // Load associated user from session
                    if (null != session) {
                        user = session.getUser();
                    }
                }
            }
        }

        // Set security context
        request.setSecurityContext(new MySecurityContext(session, user));
        return request;
    }

    @Override
    public ContainerRequestFilter getRequestFilter() {
        return this;
    }

    @Override
    public ContainerResponseFilter getResponseFilter() {
        return null;
    }
}
