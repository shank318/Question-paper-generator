package com.generator.questionpaper.repository;

import com.generator.questionpaper.models.Question;
import com.generator.questionpaper.models.QuestionDifficulty;

import java.util.List;

public interface QuestionBankRepo {

    void AddQuestion(Question question);
    Question getQuestion(int questionId);
    List<Question> getQuestions();
    List<Question> getQuestions(QuestionDifficulty questionDifficulty);
}
