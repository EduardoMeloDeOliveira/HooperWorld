package hoop.api.api.domain.quiz.DTO;

import lombok.Builder;

@Builder
public record QuizReponseDTO(
        Long quizId,
        String quizTitle,
        String quizDescription

        //TODO implementar a lista de questionDTO's no futuro
) {
}
