package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vasax32 on 01.08.14.
 */
@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idModule;

    String name;
    String description;
    String moduleImage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idCourse")
    Course course;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "module")
    private List<Lesson> lessons;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "module")
    private List<Test> tests;

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
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

    public String getModuleImage() {
        return moduleImage;
    }

    public void setModuleImage(String moduleImage) {
        this.moduleImage = moduleImage;
    }

    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @JsonIgnore
    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @JsonIgnore
    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Module module = (Module) o;

        if (idModule != module.idModule) return false;
        if (!course.equals(module.course)) return false;
        if (description != null ? !description.equals(module.description) : module.description != null) return false;
        if (moduleImage != null ? !moduleImage.equals(module.moduleImage) : module.moduleImage != null) return false;
        if (!name.equals(module.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idModule;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (moduleImage != null ? moduleImage.hashCode() : 0);
        result = 31 * result + course.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Module{" +
                "idModule=" + idModule +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", moduleImage='" + moduleImage + '\'' +
                ", course=" + course +
                '}';
    }
}
