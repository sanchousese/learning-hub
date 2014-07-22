package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;
import ua.com.learninghub.model.interfaces.HibernateL2Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasax32 on 15.07.14.
 */
@Entity
public class UserCategory implements HibernateL2Cache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserCategory;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<User> userList;

    @JsonIgnore
    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "CategoryRule",
            joinColumns = {@JoinColumn(name = "idUserCategory")},
            inverseJoinColumns = {@JoinColumn(name = "idRuleType")})
    private List<RuleType> rules;

    public int getIdUserCategory() {
        return idUserCategory;
    }

    public void setIdUserCategory(int idUserCategory) {
        this.idUserCategory = idUserCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public ArrayList<User> getUserList(){
        return new ArrayList<User>(userList);
    }

    @Deprecated
    public void removeUser(User user) {
        if(!userList.contains(user))
            return;
        //removing user from current category
        userList.remove(user);
        user.setCategory(null);
    }

    @Deprecated
    public void addUser(User user) {
        if(userList.contains(user))
            return;
        //add new user to current category
        userList.add(user);
        //setting category of user to this category
        user.setCategory(this);
    }

    @JsonIgnore
    public List<RuleType> getRules() {
        return rules;
    }

    @Deprecated
    public void setRules(List<RuleType> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "UserCategory{" +
                "idUserCategory=" + idUserCategory +
                ", name='" + name + '\'' +
                //", userList=" + userList +
                ", rules='" + rules + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCategory that = (UserCategory) o;

        if (idUserCategory != that.idUserCategory) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserCategory;
        result = 31 * result + name.hashCode();
        return result;
    }
}
