package com.generator.questionpaper.services;

import com.generator.questionpaper.models.Question;
import com.generator.questionpaper.models.QuestionDifficulty;
import com.generator.questionpaper.repository.QuestionBankRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionBankRepo questionBankRepo;

    @Override
    public List<Question> getQuestionsByTotalMarks(int marks, QuestionDifficulty difficulty) {
        final List<Question> questions = questionBankRepo.getQuestions(difficulty);
        int n = questions.size();
        if (n == 0 || marks < 0)
            throw new IllegalArgumentException("Invalid input");
        boolean[][] dp;
        dp = new boolean[n][marks + 1];
        for (int i = 0; i < n; ++i) {
            dp[i][0] = true;
        }
        if (questions.get(0).getMarks() <= marks)
            dp[0][questions.get(0).getMarks()] = true;
        for (int i = 1; i < n; ++i)
            for (int j = 0; j < marks + 1; ++j)
                dp[i][j] = (questions.get(i).getMarks() <= j) ? (dp[i - 1][j] ||
                        dp[i - 1][j - questions.get(i).getMarks()])
                        : dp[i - 1][j];
        if (dp[n - 1][marks] == false) {
            throw new IllegalArgumentException("There are no questions with difficulty " + difficulty +
                    " For Marks " + marks);
        }
        ArrayList<Question> applicableQuestions = new ArrayList<>();
        List<List<Question>> allCombinations = new ArrayList<>();
        //Backtrack
        getAllCombinations(dp, questions, n - 1, marks, applicableQuestions, allCombinations);
        return allCombinations.get(0);
    }

    void getAllCombinations(boolean[][] dp, List<Question> questions, int i, int marks,
                            ArrayList<Question> p, List<List<Question>> allCombinations) {
        if (i == 0 && marks != 0 && dp[0][marks]) {
            p.add(questions.get(i));
            allCombinations.add((ArrayList<Question>) p.clone());
            p.clear();
            return;
        }
        // If sum becomes 0
        if (i == 0 && marks == 0) {
            allCombinations.add((ArrayList<Question>) p.clone());
            p.clear();
            return;
        }
        // If given sum can be achieved after ignoring
        // current element.
        if (dp[i - 1][marks]) {
            // Create a new vector to store path
            ArrayList<Question> temp = new ArrayList<>();
            temp.addAll(p);
            getAllCombinations(dp, questions, i - 1, marks, temp, allCombinations);
        }

        // If given sum can be achieved after considering
        // current element.
        if (marks >= questions.get(i).getMarks() && dp[i - 1][marks - questions.get(i).getMarks()]) {
            p.add(questions.get(i));
            getAllCombinations(dp, questions, i - 1, marks - questions.get(i).getMarks(), p, allCombinations);
        }
    }
}
