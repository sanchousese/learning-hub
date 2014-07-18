package ua.com.learninghub.dao;

import org.junit.Test;
import ua.com.learninghub.model.dao.SubjectDao;
import ua.com.learninghub.model.entities.Subject;

import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class SubjectTests {
    private SubjectDao subjectDao = new SubjectDao();

    @Test
    public void selectAll(){
        List<Subject> subjects = subjectDao.selectAll();
        System.out.println(subjects);
    }

    @Test
    public void insertSubject(){
        Subject subject = new Subject();
        subject.setName("Subject Ins");
        subject.setDescription("Some awesome description");
        subject.setLogoPath("/img/2.jpg");
        subjectDao.insert(subject);
    }



}
