package ua.com.learninghub.model.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vasax32 on 16.07.14.
 */
@Entity
public class RuleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRuleType;

    private String name;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean enabled;

    @ManyToMany(mappedBy = "rules")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserCategory> userCategories;

    public int getIdRuleType() {
        return idRuleType;
    }

    public void setIdRuleType(int idRuleType) {
        this.idRuleType = idRuleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserCategory> getUserCategories() {
        return userCategories;
    }

    @Deprecated
    public void setUserCategories(List<UserCategory> userCategories) {
        this.userCategories = userCategories;
    }

    @Override
    public String toString() {
        return "RuleType{" +
                "idRuleType=" + idRuleType +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleType ruleType = (RuleType) o;

        if (enabled != ruleType.enabled) return false;
        if (idRuleType != ruleType.idRuleType) return false;
        if (!name.equals(ruleType.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRuleType;
        result = 31 * result + name.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
