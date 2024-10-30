package hoop.api.api.domain.quiz.DTO;

import jakarta.validation.constraints.NotBlank;

public record QuizRequestDTO(

        @NotBlank
        String quizTitle,

        @NotBlank
        String quizDescription
) {
}
