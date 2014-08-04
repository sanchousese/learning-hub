package EntitiesTests;

import ua.com.learninghub.model.dao.implementation.AnswerDaoImpl;
import ua.com.learninghub.model.dao.implementation.QuestionDaoImpl;
import ua.com.learninghub.model.dao.interfaces.AnswerDao;
import ua.com.learninghub.model.entities.Answer;
import ua.com.learninghub.model.entities.Question;

import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
public class AnswerTests {
    private AnswerDao answerDao = new AnswerDaoImpl();

    @org.junit.Test
    public void insert(){
        Answer answer = new Answer();
        answer.setAns("INSERT TEST");
        answer.setQuestion((new QuestionDaoImpl()).selectById(1));
        //test.setModule((new ModuleDaoImpl()).selectById(1));
        answerDao.insert(answer);
    }


    @org.junit.Test
    public void update(){
        Answer answer = answerDao.selectById(1);
        answer.setAns("Answer NEW");
        answer.setCorrect(true);
        answer.setQuestion(new Question());
        //test.setModule((new ModuleDaoImpl()).selectById(2));
        answerDao.update(answer);
    }

    @org.junit.Test
    public void selectAll(){
        List<Answer> answers = answerDao.selectAll();
        for (Answer answer : answers)
            System.out.println(answer);
    }

}
