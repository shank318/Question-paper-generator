package com.generator.questionpaper.services;

import com.generator.questionpaper.models.Question;
import com.generator.questionpaper.models.QuestionDifficulty;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsByTotalMarks(int marks, QuestionDifficulty difficulty);
}
