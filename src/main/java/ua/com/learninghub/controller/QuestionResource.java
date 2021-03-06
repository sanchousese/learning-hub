package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.controller.auth.CookieUtil;
import ua.com.learninghub.controller.auth.SessionIdentifierGenerator;
import ua.com.learninghub.model.dao.implementation.*;
import ua.com.learninghub.model.dao.interfaces.*;
import ua.com.learninghub.model.entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
@Path("/questions")
public class QuestionResource {

    QuestionDao questionDao = new QuestionDaoImpl();
    TestDao testDao= new TestDaoImpl();
    AnswerDao answerDao = new AnswerDaoImpl();
    private LessonDao lessonDao = new LessonDaoImpl();
    private UserLessonDao userLessonDao = new UserLessonDaoImpl();
    SessionDao sessionDao = new SessionDaoImpl();
    CookieUtil cookieUtil = new CookieUtil();

    SessionIdentifierGenerator sessionIdentifierGenerator = new SessionIdentifierGenerator();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions(@QueryParam(value = "idLesson") int lessonId) {

        Lesson lesson = lessonDao.selectById(lessonId);
        Test test = lesson.getTest();

        if (test == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Question> questions = test.getQuestions();

        if (questions == null || questions.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Question>>(questions) {
        }).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/question/{idQuestion}") // ...8080/rest/courses/1234
    public Response getCourse(@PathParam("idQuestion") int questionID) {

        Question question = questionDao.selectById(questionID);

        if (question == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().entity(question).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(JSONObject object) throws JSONException {
        int lessonId = object.getInt("lessonId");
        JSONObject data = object.getJSONObject("data");
        Question question = new Question();
        question.setQue(data.getString("que"));
        Lesson lesson = lessonDao.selectById(lessonId);
        Test  test = lesson.getTest();
        if(test == null){
            test = new Test();
            test.setName("Test 1");
            test.setLesson(lesson);
            testDao.insert(test);
        }
        question.setTest(test);

        if(questionDao.insert(question)) {

            Answer answer = new Answer();
            answer.setAns(data.getString("ans1"));
            answer.setCorrect(data.getBoolean("ans1b"));
            answer.setQuestion(question);
            answerDao.insert(answer);

            answer = new Answer();
            answer.setAns(data.getString("ans2"));
            answer.setCorrect(data.getBoolean("ans2b"));
            answer.setQuestion(question);
            answerDao.insert(answer);

            answer = new Answer();
            answer.setAns(data.getString("ans3"));
            answer.setCorrect(data.getBoolean("ans3b"));
            answer.setQuestion(question);
            answerDao.insert(answer);

            answer = new Answer();
            answer.setAns(data.getString("ans4"));
            answer.setCorrect(data.getBoolean("ans4b"));
            answer.setQuestion(question);
            answerDao.insert(answer);

            return Response.ok(new String(Integer.toString(question.getIdQuestion()))).build();
        } else return Response.status(401).build();
    }

    @POST
    @Path("/validateAnswers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateAnswers(List<Question> ques, @Context HttpServletRequest hsr){
        double sum = 0;
        for(Question question : ques){
            List<Answer> masterAnswers = questionDao.selectById(question.getIdQuestion()).getAnswers();
            List<Answer> answers = question.getAnswers();
            double per = ((double)100)/((double)answers.size());
            double ansSum = 0;
            boolean allFalse = true;
            for(int i = 0; i <answers.size(); i++){
                if(answers.get(i).getCorrect()){
                    allFalse = false;
                    break;
                }
            }
            if(!allFalse) {
                for (int i = 0; i < answers.size(); i++) {
                    if (answers.get(i).equals(masterAnswers.get(i)))
                        ansSum += per;
                }
                sum += ansSum;
            }
        }
        sum /= ques.size();
        UserLesson userLesson = new UserLesson();
        userLesson.setScore(sum);
        userLesson.setLesson(questionDao.selectById(ques.get(0).getIdQuestion()).getTest().getLesson());
        userLesson.setUser(sessionDao.selectBySessionId(cookieUtil.getSessionIdFromRequest(hsr)).getUser());
        userLessonDao.insert(userLesson);
        return Response.ok(sum).build();
    }
}
