package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Table;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
@Entity
public class Course implements HibernateL2Cache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCourse;

    String name;
    @XmlTransient
    Date beginDate;
    @XmlTransient
    Date endDate;
    String description;
    int price;
    int rate;
    String mainImagePath;
    String mainVideoPath;

    @JsonIgnore
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "UserCourse", joinColumns = {@JoinColumn(name = "idUser")}, inverseJoinColumns = {@JoinColumn(name = "idCourse")})
    private List<User> users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idSubject")
    private Subject subject;

    public Course(){}

    public Course(String name, String description, int price, String mainImagePath) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.mainImagePath = mainImagePath;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @XmlTransient
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @JsonIgnore
    public List<User> getUsers() {
        return users;
    }

    @Deprecated
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonIgnore
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getMainImagePath() {
        return mainImagePath;
    }

    public void setMainImagePath(String mainImagePath) {
        this.mainImagePath = mainImagePath;
    }

    public String getMainVideoPath() {
        return mainVideoPath;
    }

    public void setMainVideoPath(String mainVideoPath) {
        this.mainVideoPath = mainVideoPath;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", name='" + name + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (idCourse != course.idCourse) return false;
        if (price != course.price) return false;
        if (rate != course.rate) return false;
//        if (!beginDate.equals(course.beginDate)) return false;
        if (!description.equals(course.description)) return false;
//        if (!endDate.equals(course.endDate)) return false;
        if (!name.equals(course.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCourse;
        result = 31 * result + name.hashCode();
        result = 31 * result + beginDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price;
        result = 31 * result + rate;
        return result;
    }
}

