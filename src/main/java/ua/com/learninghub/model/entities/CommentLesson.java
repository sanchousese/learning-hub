package ua.com.learninghub.model.entities;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Max on 05.08.2014.
 */
@Entity
public class CommentLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCommentLesson;

    String body;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idLesson")
    Lesson lesson;

    public int getIdCommentLesson() {
        return idCommentLesson;
    }

    public void setIdCommentLesson(int idCommentLesson) {
        this.idCommentLesson = idCommentLesson;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonIgnore
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentLesson)) return false;

        CommentLesson that = (CommentLesson) o;

        if (idCommentLesson != that.idCommentLesson) return false;
        if (!body.equals(that.body)) return false;
        if (!lesson.equals(that.lesson)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCommentLesson;
        result = 31 * result + body.hashCode();
        result = 31 * result + lesson.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CommentLesson{" +
                "idCommentLesson=" + idCommentLesson +
                ", body='" + body + '\'' +
                ", lesson=" + lesson +
                '}';
    }
}
