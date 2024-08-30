package com.quiz.quizservice.service;


import com.quiz.quizservice.dao.QuizDao;
import com.quiz.quizservice.entity.Quiz;
import com.quiz.quizservice.feign.QuizFeignInterface;
import com.quiz.quizservice.model.QuizModel;
import com.quiz.quizservice.model.QuizSubmitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuizService {
//    private final QuestionDao questionDao;
    private final QuizFeignInterface quizFeignInterface;
    private final QuizDao quizDao;

    public ResponseEntity<String> createQuiz(String category, int numOfQ, String title) {
        //call question service. url -> http://localhost:8080/question/generate
        //we don't want to hardcoded the url.So we need to use open feign.And we also use eureka server for discover every microservice.

        List<Integer> question = quizFeignInterface.getQuestionsForQuiz(category,numOfQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(question);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizModel>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isPresent()){
            List<Integer> questionIds = quiz.get().getQuestionsIds();
            return quizFeignInterface.getQuestionsFromId(questionIds);
        }else{
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Integer> submitQuiz(List<QuizSubmitResponse> response) {
        return quizFeignInterface.getScore(response);
    }
}
