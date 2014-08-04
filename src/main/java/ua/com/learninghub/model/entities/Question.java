package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idQuestion;

    String que;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idTest")
    Test test;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (idQuestion != question.idQuestion) return false;
        if (!que.equals(question.que)) return false;
        if (!test.equals(question.test)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idQuestion;
        result = 31 * result + que.hashCode();
        result = 31 * result + test.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "idQuestion=" + idQuestion +
                ", que='" + que + '\'' +
                ", test=" + test +
                '}';
    }


}
