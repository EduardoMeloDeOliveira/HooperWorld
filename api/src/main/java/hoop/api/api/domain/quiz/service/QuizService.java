package hoop.api.api.domain.quiz.service;

import hoop.api.api.domain.quiz.DTO.QuizReponseDTO;
import hoop.api.api.domain.quiz.DTO.QuizRequestDTO;
import hoop.api.api.domain.quiz.entity.Quiz;
import hoop.api.api.domain.quiz.mapper.QuizMapper;
import hoop.api.api.domain.quiz.repository.QuizRepository;
import hoop.api.api.handler.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public QuizReponseDTO createQuiz(QuizRequestDTO requestDto) {

        Quiz quiz = QuizMapper.toEntity(requestDto);
        quizRepository.save(quiz);
        return QuizMapper.toDto(quiz);
    }

    public List<QuizReponseDTO> getAllQuiz() {
        return quizRepository.findAll().stream().map(q -> QuizMapper.toDto(q)).collect(Collectors.toList());
    }

    public QuizReponseDTO getQuizById(Long id) {

        return QuizMapper.toDto(quizRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("quiz not found")));
    }

    public void deleteQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("quiz not found"));
        quizRepository.delete(quiz);
    }

    public Quiz existsQuizById(Long id) {

        return quizRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("quiz not found"));

    }
}
