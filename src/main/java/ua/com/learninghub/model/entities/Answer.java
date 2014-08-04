package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.*;

/**
 * Created by Max on 04.08.2014.
 */
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idAnswer;

    String ans;

    boolean correct;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idQuestion")
    Question question;



    public int getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @JsonIgnore
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer = (Answer) o;

        if (correct != answer.correct) return false;
        if (idAnswer != answer.idAnswer) return false;
        if (!ans.equals(answer.ans)) return false;
        if (!question.equals(answer.question)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAnswer;
        result = 31 * result + ans.hashCode();
        result = 31 * result + (correct ? 1 : 0);
        result = 31 * result + question.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "idAnswer=" + idAnswer +
                ", ans='" + ans + '\'' +
                ", correct=" + correct +
                ", question=" + question +
                '}';
    }
}
