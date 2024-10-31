package hoop.api.api.domain.answer.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequestDTO(
        @NotBlank
        String answerContent,

        @NotNull
        Boolean isCorret
) {
}
