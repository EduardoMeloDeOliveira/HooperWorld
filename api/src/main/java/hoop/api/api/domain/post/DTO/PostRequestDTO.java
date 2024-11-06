package hoop.api.api.domain.post.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PostRequestDTO(
        @NotBlank
        String title,

        @NotBlank
        String content

) {
}
