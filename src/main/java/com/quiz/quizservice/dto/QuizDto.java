package com.quiz.quizservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class QuizDto {
    String categoryName;
    Integer numOfQuestions;
    String title;
}
