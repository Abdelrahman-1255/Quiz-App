package com.quiz_service.service;

import java.lang.foreign.Linker.Option;
import java.net.ResponseCache;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz_service.dao.QuizDao;
import com.quiz_service.model.Question;
import com.quiz_service.model.QuestionWrapper;
import com.quiz_service.model.Quiz;
import com.quiz_service.model.Response;

@Service    
public class QuizService {


    @Autowired
    private QuizDao quizDao;

    // @Autowired
    // private QuestionService questionService;

    // public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
    //     List<Question> questions = questionService.getRandomQuestionsByCategory(category, numQ);
    //     Quiz quiz = new Quiz();
    //     quiz.setTitle(title);
    //     quiz.setQuestions(questions);
    //     quizDao.save(quiz);
    //     return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    // }

    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createQuiz'");
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quizOpt = quizDao.findById(id);
        if (quizOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quiz quiz = quizOpt.get();
        List<QuestionWrapper> questionWrappers = quiz.getQuestions().stream()
                .map(question -> new QuestionWrapper(
                        question.getId(),
                        question.getQuestionTitle(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
       Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

    

}
