package com.quiz.quizservice.controller;

import com.quiz.quizservice.dto.QuizDto;
import com.quiz.quizservice.model.QuizModel;
import com.quiz.quizservice.model.QuizSubmitResponse;
import com.quiz.quizservice.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("quiz/")
public class QuizController {


    private final QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto dto) {
        return quizService.createQuiz(dto.getCategoryName(), dto.getNumOfQuestions(), dto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizModel>> getQuizQuestion(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<QuizSubmitResponse> response) {
        return quizService.submitQuiz( response);
    }
}
