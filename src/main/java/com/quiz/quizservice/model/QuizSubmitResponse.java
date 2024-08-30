package com.quiz.quizservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizSubmitResponse {
    String id;
    String response;
}
