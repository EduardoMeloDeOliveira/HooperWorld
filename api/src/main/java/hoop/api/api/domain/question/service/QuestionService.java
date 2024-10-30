package hoop.api.api.domain.question.service;

import hoop.api.api.domain.question.DTO.QuestionRequestDTO;
import hoop.api.api.domain.question.DTO.QuestionResponseDTO;
import hoop.api.api.domain.question.entity.Question;
import hoop.api.api.domain.question.mapper.QuestionMapper;
import hoop.api.api.domain.question.repository.QuestionRepository;
import hoop.api.api.domain.quiz.entity.Quiz;
import hoop.api.api.domain.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizService quizService;

    public QuestionResponseDTO atributeQuestionToQuiz(Long quizId,QuestionRequestDTO requestDTO){
        Quiz quiz = quizService.existsQuizById(quizId);
        Question question = QuestionMapper.toEntity(requestDTO);
        question.setQuiz(quiz);
        return QuestionMapper.toDTO(questionRepository.save(question));
    }
}
