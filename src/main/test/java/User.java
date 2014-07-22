import java.util.HashSet;
import java.util.Set;

/**
 * Created by vasax32 on 21.07.14.
 */
public class User implements java.security.Principal {
    public static enum Role {
        Editor, Visitor, Contributor
    };

    private String userId;          // id
    private String name;            // name
    private String emailAddress;    // email
    private Set<Role> roles = new HashSet<Role>();        // roles

    @Override
    public String getName() {
        return name;
    }

    public User(){}

    public User(String id, String name, String email, Role role){
        userId = id;
        this.name = name;
        this.emailAddress = email;
        roles.add(role);
    }

    public User(String id, String name, String email){
        userId = id;
        this.name = name;
        this.emailAddress = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
