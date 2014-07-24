package ua.com.learninghub.controller.auth;

import com.sun.jersey.api.core.HttpRequestContext;
import ua.com.learninghub.model.dao.implementation.SessionDaoImpl;
import ua.com.learninghub.model.dao.interfaces.SessionDao;
import ua.com.learninghub.model.entities.Session;
import ua.com.learninghub.model.entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vasax32 on 24.07.14.
 */
public class CookieUtil {

    private SessionDao sessionDao = new SessionDaoImpl();
    private SessionIdentifierGenerator sessionIdentifierGenerator = new SessionIdentifierGenerator();

    public String getSessionIdFromRequest(HttpServletRequest hsr) {
        if (hsr == null) return null;
        Cookie[] cookies = hsr.getCookies();
        for (Cookie c : cookies)
            if (c.getName().equals("sessionUID")) {
                return c.getValue();
            }
        return null;
    }

    //inserts in database and cookie
    public boolean insertSessionUID(HttpServletResponse rspn, User user) {
        String sessionUID = sessionIdentifierGenerator.nextSessionId();
        if (sessionDao.insert(new Session(sessionUID, user))) {
            Cookie newCookie = new Cookie("sessionUID", sessionUID);
            rspn.addCookie(newCookie);
            return true;
        } else return false;
    }

    public boolean removeSessionUID(HttpServletRequest hsr, HttpServletResponse rspn){
        String sessionId = getSessionIdFromRequest(hsr);
        if(sessionId != null && sessionDao.deleteBySessionId(sessionId)) {
            Cookie invalidateCookie = new Cookie("sessionUID", sessionId);
            invalidateCookie.setMaxAge(0);
            rspn.addCookie(invalidateCookie);
            return true;
        }
        return false;
    }
}
