package ua.com.learninghub.model.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vasax32 on 15.07.14.
 */
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    private String login;
    private String pass;
    private String email;
    private int money;

    @ManyToOne
    @JoinColumn(name = "idUserCategory")
    private UserCategory category;

    @ManyToMany(mappedBy = "users")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> courses;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public UserCategory getCategory() {
        return category;
    }

    public void setCategory(UserCategory category) {
        //if nothing changed - exit
        if(sameAsFormer(category))
            return;
        //setting new category
        UserCategory oldCategory = this.category;
        this.category = category;
        //remove user form old category
        if(oldCategory != null)
            oldCategory.removeUser(this);
        //and add it to new category
        if(category != null)
            category.addUser(this);
    }

    private boolean sameAsFormer(UserCategory newOwner) {
        return category ==null? newOwner == null : category.equals(newOwner);
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Deprecated
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", money=" + money +
                ", category=" + category +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != user.idUser) return false;
        if (money != user.money) return false;
        if (category != null ? !category.equals(user.category) : user.category != null) return false;
        if (!email.equals(user.email)) return false;
        if (!login.equals(user.login)) return false;
        if (!pass.equals(user.pass)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + login.hashCode();
        result = 31 * result + pass.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + money;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
