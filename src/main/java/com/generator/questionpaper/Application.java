package com.generator.questionpaper;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.questionpaper.dto.QuestionsGeneratorInput;
import com.generator.questionpaper.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application  implements CommandLineRunner {

    @Autowired
    QuestionPaperGenerator questionPaperGenerator;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        QuestionsGeneratorInput input = new QuestionsGeneratorInput();
        input.setTotalMarks(100);
        input.setEasyDifficultyPer(20);
        input.setHardDifficultyPer(80);
        input.setMediumDifficultyPer(0);
        questionPaperGenerator.generatePaper(input);
    }

}
