package EntitiesTests;

import ua.com.learninghub.model.dao.implementation.QuestionDaoImpl;
import ua.com.learninghub.model.dao.implementation.TestDaoImpl;
import ua.com.learninghub.model.dao.interfaces.QuestionDao;
import ua.com.learninghub.model.entities.Question;
import ua.com.learninghub.model.entities.Test;

import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
public class QuestionTests {
    private QuestionDao questionDao = new QuestionDaoImpl();

    @org.junit.Test
    public void insert(){
        Question question = new Question();
        question.setQue("Question 1");
        question.setTest((new TestDaoImpl().selectById(1)));
        //test.setModule((new ModuleDaoImpl()).selectById(1));
       questionDao.insert(question);
    }

    @org.junit.Test
    public void update(){
        Question question = questionDao.selectById(1);
        question.setQue("Question NEW");
        question.setTest((new TestDaoImpl().selectById(1)));
        questionDao.update(question);
    }

    @org.junit.Test
    public void selectAll(){
        List<Question> questions = questionDao.selectAll();
        for (Question question : questions)
            System.out.println(question);
    }

}
