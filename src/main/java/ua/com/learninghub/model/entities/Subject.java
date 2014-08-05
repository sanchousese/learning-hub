package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
@Entity
public class Subject implements HibernateL2Cache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idSubject;

    String name;
    @Column(columnDefinition = "text")
    String description;
    String logoPath;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "subject")
    private List<Course> courses;

    //s@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idDiscipline")
    private Discipline discipline;

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @JsonIgnore
    public ArrayList<Course> getCourses() {
        return new ArrayList<Course>(courses);
    }

    @Deprecated
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    //@JsonIgnore
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "idSubject=" + idSubject +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logoPath='" + logoPath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (idSubject != subject.idSubject) return false;
        if (!description.equals(subject.description)) return false;
        if (logoPath != null ? !logoPath.equals(subject.logoPath) : subject.logoPath != null) return false;
        if (!name.equals(subject.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSubject;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (logoPath != null ? logoPath.hashCode() : 0);
        return result;
    }
}
