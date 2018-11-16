package com.generator.questionpaper.dto;

import lombok.Data;

@Data
public class QuestionsGeneratorInput {
    int totalMarks;
    int easyDifficultyPer;
    int mediumDifficultyPer;
    int hardDifficultyPer;

}
