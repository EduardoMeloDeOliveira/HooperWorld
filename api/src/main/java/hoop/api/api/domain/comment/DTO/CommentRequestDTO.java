package hoop.api.api.domain.comment.DTO;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDTO(
        @NotBlank
        String content
) {
}
