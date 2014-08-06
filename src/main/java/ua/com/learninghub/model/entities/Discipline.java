package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vasax32 on 24.07.14.
 */
@Entity
public class Discipline implements HibernateL2Cache{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDiscipline;

    private String name;
    @Column(columnDefinition="text)")
    private String description;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idSpecialty")
    private Specialty specialty;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "discipline")
    private List<Subject> subjects;

    public int getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(int idDiscipline) {
        this.idDiscipline = idDiscipline;
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

    //@JsonIgnore
    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    @JsonIgnore
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "idDiscipline=" + idDiscipline +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", specialty=" + specialty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discipline that = (Discipline) o;

        if (idDiscipline != that.idDiscipline) return false;
        if (!description.equals(that.description)) return false;
        if (!name.equals(that.name)) return false;
        if (!specialty.equals(that.specialty)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDiscipline;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + specialty.hashCode();
        return result;
    }
}
