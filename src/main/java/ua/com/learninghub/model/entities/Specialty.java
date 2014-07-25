package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import ua.com.learninghub.model.dao.interfaces.HibernateL2Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasax32 on 24.07.14.
 */
@Entity
public class Specialty implements HibernateL2Cache{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idSpecialty;

    String name;
    @Column(columnDefinition="text)")
    String description;

    @JsonIgnore
    @OneToMany(mappedBy = "specialty", fetch = FetchType.EAGER)
    List<Discipline> disciplines;

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
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

    @JsonIgnore
    public List<Discipline> getDisciplines() {
        return new ArrayList<Discipline>(disciplines);
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "idSpecialty=" + idSpecialty +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Specialty specialty = (Specialty) o;

        if (idSpecialty != specialty.idSpecialty) return false;
        if (!description.equals(specialty.description)) return false;
        if (!name.equals(specialty.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSpecialty;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
