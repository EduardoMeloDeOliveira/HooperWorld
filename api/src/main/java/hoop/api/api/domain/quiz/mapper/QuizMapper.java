package hoop.api.api.domain.quiz.mapper;

import hoop.api.api.domain.question.mapper.QuestionMapper;
import hoop.api.api.domain.quiz.DTO.QuizReponseDTO;
import hoop.api.api.domain.quiz.DTO.QuizRequestDTO;
import hoop.api.api.domain.quiz.entity.Quiz;

import java.util.stream.Collectors;

public class QuizMapper {

    public static QuizReponseDTO toDto(Quiz quiz) {

        return QuizReponseDTO.builder()
                .quizId(quiz.getId())
                .quizTitle(quiz.getQuizTitle())
                .quizDescription(quiz.getQuizDescription())
                .questions(quiz.getQuestions().stream().map(q-> QuestionMapper.toDTO(q)).collect(Collectors.toList()))
                .build();
    }

    public static Quiz toEntity(QuizRequestDTO dto) {
        return Quiz.builder()
                .id(null)
                .quizTitle(dto.quizTitle())
                .quizDescription(dto.quizDescription())
                .build();

    }
}
