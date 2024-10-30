package hoop.api.api.domain.question.DTO;

import jakarta.validation.constraints.NotBlank;

public record QuestionRequestDTO(
        @NotBlank
        String questionContent
) {
}
