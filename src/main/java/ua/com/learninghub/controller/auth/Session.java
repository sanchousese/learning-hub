package ua.com.learninghub.controller.auth;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vasax32 on 21.07.14.
 */
public class Session implements Serializable {
    private static final long serialVersionUID = -7483170872690892182L;

    private String sessionId;   // id
    private int userId;      // user
    private boolean active;     // session active?
    private boolean secure;     // session secure?

    private Date createTime;    // session create time
    private Date lastAccessedTime; // session last use time

    public Session(){}

    public Session(String sessionId, int userId){
        this.sessionId = sessionId;
        this.userId = userId;
        active = true;
        secure = true;

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Date lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId='" + sessionId + '\'' +
                ", userId='" + userId + '\'' +
                ", active=" + active +
                ", secure=" + secure +
                ", createTime=" + createTime +
                ", lastAccessedTime=" + lastAccessedTime +
                '}';
    }
}
