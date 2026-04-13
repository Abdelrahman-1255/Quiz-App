package com.quiz_service.dto;

import lombok.Data;

@Data
public class QuizDto {
    String category;
    int numQuestions;
    String title;
}
