package ua.com.learninghub.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ua.com.learninghub.controller.auth.SessionIdentifierGenerator;
import ua.com.learninghub.model.dao.implementation.*;
import ua.com.learninghub.model.dao.interfaces.*;
import ua.com.learninghub.model.entities.*;

import javax.ws.rs.*;
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
    public Response create(Question question, List<Answer> answers) {
        question.setAnswers(answers);

        if(questionDao.insert(question)) {
            return Response.ok(new String(Integer.toString(question.getIdQuestion()))).build();
        } else return Response.status(401).build();
    }
}
