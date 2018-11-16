package com.generator.questionpaper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.questionpaper.dto.QuestionsGeneratorInput;
import com.generator.questionpaper.models.Question;
import com.generator.questionpaper.models.QuestionDifficulty;
import com.generator.questionpaper.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionPaperGenerator {

    @Autowired
    QuestionService questionService;

    public void generatePaper(QuestionsGeneratorInput input) {
        if(input.getTotalMarks()<=0) throw new IllegalArgumentException("Invalid total marks");
        if (input.getEasyDifficultyPer() + input.getHardDifficultyPer() + input.getMediumDifficultyPer() != 100)
            throw new IllegalArgumentException("Invalid sum of percentage");
        if (!isValidPercentage(input.getTotalMarks(), input.getEasyDifficultyPer()) ||
                !isValidPercentage(input.getTotalMarks(), input.getHardDifficultyPer()) ||
                !isValidPercentage(input.getTotalMarks(), input.getMediumDifficultyPer()))
            throw new IllegalArgumentException("Invalid percentages");

        if(input.getEasyDifficultyPer()>0){
            final int easyTotalMarks  = input.getTotalMarks() * input.getEasyDifficultyPer() / 100;
            System.out.println("Generating EASY questions of marrks :: "+easyTotalMarks);
            System.out.println(Arrays.asList(questionService.getQuestionsByTotalMarks(easyTotalMarks, QuestionDifficulty.EASY)));
        }

        if(input.getMediumDifficultyPer()>0){
            final int mediumTotalMarks  = input.getTotalMarks() * input.getMediumDifficultyPer() / 100;
            System.out.println("Generating MEDIUM questions of marks :: "+mediumTotalMarks);
            System.out.println(Arrays.asList(questionService.getQuestionsByTotalMarks(mediumTotalMarks,QuestionDifficulty.MEDIUM)));
        }

        if(input.getHardDifficultyPer()>0){
            final int hardTotalMarks  = input.getTotalMarks() * input.getHardDifficultyPer() / 100;
            System.out.println("Generating HARD questions of marks :: "+hardTotalMarks);
            System.out.println(Arrays.asList(questionService.getQuestionsByTotalMarks(hardTotalMarks,QuestionDifficulty.HARD)));
        }


    }


    private boolean isValidPercentage(int totalMarks, int percentage) {
        final double i = totalMarks * percentage / 100.0;
        if ((i == Math.floor(i)) && !Double.isInfinite(i)) {
            return true;
        }
        return false;
    }

}
