package ua.com.learninghub.model.entities;

import javax.persistence.*;

/**
 * Created by vasax32 on 06.08.14.
 */
@Entity
public class UserLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserLesson;

    @ManyToOne
    @JoinColumn(name = "idUser")
    User user;

    @ManyToOne
    @JoinColumn(name = "idLesson")
    Lesson lesson;

    private double score;

    public int getIdUserLesson() {
        return idUserLesson;
    }

    public void setIdUserLesson(int idUserLesson) {
        this.idUserLesson = idUserLesson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLesson that = (UserLesson) o;

        if (idUserLesson != that.idUserLesson) return false;
        if (Double.compare(that.score, score) != 0) return false;
        if (!lesson.equals(that.lesson)) return false;
        if (!user.equals(that.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idUserLesson;
        result = 31 * result + user.hashCode();
        result = 31 * result + lesson.hashCode();
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserLesson{" +
                "idUserLesson=" + idUserLesson +
                ", user=" + user +
                ", lesson=" + lesson +
                ", score=" + score +
                '}';
    }
}
