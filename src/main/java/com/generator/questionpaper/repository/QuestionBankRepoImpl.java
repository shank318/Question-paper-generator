package com.generator.questionpaper.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.questionpaper.models.Question;
import com.generator.questionpaper.models.QuestionDifficulty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionBankRepoImpl implements QuestionBankRepo {

    List<Question> questions = new ArrayList<>();

    @PostConstruct
    void init() {
        try {
            File file = ResourceUtils.getFile("classpath:questions.json");
            ObjectMapper objectMapper = new ObjectMapper();
            List<Question> myObjects = objectMapper.readValue(file, new TypeReference<List<Question>>() {
            });
            System.out.println("Read questions from Json file");
            questions.addAll(myObjects);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AddQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public Question getQuestion(int questionId) {
        return null;
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public List<Question> getQuestions(QuestionDifficulty questionDifficulty) {
        return questions.stream().filter(question -> question.getDifficulty().equals(questionDifficulty)).collect(Collectors.toList());
    }
}
