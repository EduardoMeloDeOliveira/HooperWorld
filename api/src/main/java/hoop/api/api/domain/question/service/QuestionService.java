package hoop.api.api.domain.question.service;

import hoop.api.api.domain.question.repository.QuestionRepository;
import hoop.api.api.domain.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizService quizService;
}
