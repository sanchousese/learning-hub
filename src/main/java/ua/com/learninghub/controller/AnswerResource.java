package ua.com.learninghub.controller;

import ua.com.learninghub.model.dao.implementation.QuestionDaoImpl;
import ua.com.learninghub.model.dao.interfaces.QuestionDao;
import ua.com.learninghub.model.entities.Answer;
import ua.com.learninghub.model.entities.Question;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Max on 04.08.2014.
 */
@Path("/answers")
public class AnswerResource {

    QuestionDao questionDao = new QuestionDaoImpl();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnswers(@QueryParam(value = "idQuestion") int questionId) {


        Question question = questionDao.selectById(questionId);

        if (question == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Answer> answers = question.getAnswers();

        if (answers == null || answers.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Answer>>(answers) {
        }).build();
    }

}
