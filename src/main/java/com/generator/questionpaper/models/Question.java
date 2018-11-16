package com.generator.questionpaper.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Question {
    int questionId;
    String text;
    String subject;
    String topic;
    QuestionDifficulty difficulty;
    int marks;
}
