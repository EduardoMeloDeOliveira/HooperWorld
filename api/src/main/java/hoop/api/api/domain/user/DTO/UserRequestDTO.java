package hoop.api.api.domain.user.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record UserRequestDTO (
        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank String password
){
}
