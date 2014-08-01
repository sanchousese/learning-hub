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

    String description;
    String lessonVideo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idModule")
    Module module;

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idModule) {
        this.idLesson = idModule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLessonVideo() {
        return lessonVideo;
    }

    public void setLessonVideo(String moduleImage) {
        this.lessonVideo = moduleImage;
    }

    @JsonIgnore
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

        Lesson module = (Lesson) o;

        if (idLesson != module.idLesson) return false;
        if (!module.equals(module.module)) return false;
        if (description != null ? !description.equals(module.description) : module.description != null) return false;
        if (lessonVideo != null ? !lessonVideo.equals(module.lessonVideo) : module.lessonVideo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLesson;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lessonVideo != null ? lessonVideo.hashCode() : 0);
        result = 31 * result + module.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Module{" +
                "idLesson=" + idLesson +
                ", description='" + description + '\'' +
                ", lessonVideo='" + lessonVideo + '\'' +
                ", module=" + module +
                '}';
    }
}
