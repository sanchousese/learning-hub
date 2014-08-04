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
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idLesson;

    String name;
    String description;
    String lessonVideo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idCourse")
    Course course;

    @JsonIgnore
    @OneToOne(mappedBy = "lesson")
    private Test test;


    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public String getLessonVideo() {
        return lessonVideo;
    }

    public void setLessonVideo(String lessonVideo) {
        this.lessonVideo = lessonVideo;
    }

    @JsonIgnore
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (idLesson != lesson.idLesson) return false;
        if (course != null ? !course.equals(lesson.course) : lesson.course != null) return false;
        if (description != null ? !description.equals(lesson.description) : lesson.description != null) return false;
        if (lessonVideo != null ? !lessonVideo.equals(lesson.lessonVideo) : lesson.lessonVideo != null) return false;
        if (!name.equals(lesson.name)) return false;
        if (test != null ? !test.equals(lesson.test) : lesson.test != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLesson;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lessonVideo != null ? lessonVideo.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (test != null ? test.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "idLesson=" + idLesson +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lessonVideo='" + lessonVideo + '\'' +
                ", course=" + course +
                ", test=" + test +
                '}';
    }
}
