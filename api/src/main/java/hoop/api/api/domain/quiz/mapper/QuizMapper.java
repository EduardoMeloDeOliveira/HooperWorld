package hoop.api.api.domain.quiz.mapper;

import hoop.api.api.domain.quiz.DTO.QuizReponseDTO;
import hoop.api.api.domain.quiz.DTO.QuizRequestDTO;
import hoop.api.api.domain.quiz.entity.Quiz;

public class QuizMapper {

    public static QuizReponseDTO toDto(Quiz quiz) {

        return QuizReponseDTO.builder()
                .quizId(quiz.getId())
                .quizTitle(quiz.getQuizTitle())
                .quizDescription(quiz.getQuizDescription())
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
