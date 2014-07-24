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
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by vasax32 on 21.07.14.
 */
@Provider    // register as jersey's provider
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter {

    public static final long timeout = 3600000;

    SecurityContextFilter(){
        (new Thread(new Runnable() {
            public void run() {
                while (true){
                    try{
                        (new SessionDaoImpl()).clearTimeout(timeout);
                        Thread.sleep(1800000);
                    } catch (InterruptedException e){

                    }
                }
            }
        })).start();


    }

    private SessionDaoImpl sessionDaoImpl = new SessionDaoImpl();

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        User user = null;
        Session session = null;

        // Get session id from request header
        java.util.Map<String, Cookie> cookies =  request.getCookies();

        if(cookies != null) {

            //Cookie sessionCookie = cookies.get("JSESSIONID");
            Cookie sessionCookie = cookies.get("sessionUID");
            //System.out.println(sessionCookie);
            if(sessionCookie != null) {

                final String sessionId = sessionCookie.getValue();
                //System.out.println("Find cookie: " + sessionId);

                if (sessionId != null && sessionId.length() > 0) {
                    // Load session object from repository
                    session = sessionDaoImpl.selectBySessionId(sessionId);

                    // Load associated user from session
                    if (null != session) {
                        session.setLastAccessedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
                        sessionDaoImpl.update(session);
                        user = session.getUser();
                    }
                }
            } else System.out.println("Session cookie null");
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
