package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Max on 05.08.2014.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idComment;

    String body;

    Timestamp date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idCourse")
    Course course;

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (idComment != comment.idComment) return false;
        if (!body.equals(comment.body)) return false;
        if (!course.equals(comment.course)) return false;
        if (!date.equals(comment.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idComment;
        result = 31 * result + body.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + course.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", course=" + course +
                '}';
    }
}
