package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * Created by vasax32 on 01.08.14.
 */
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTest;

    String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idModule")
    Module module;

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (idTest != test.idTest) return false;
        if (!module.equals(test.module)) return false;
        if (name != null ? !name.equals(test.name) : test.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTest;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + module.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "idTest=" + idTest +
                ", name='" + name + '\'' +
                ", module=" + module +
                '}';
    }
}
