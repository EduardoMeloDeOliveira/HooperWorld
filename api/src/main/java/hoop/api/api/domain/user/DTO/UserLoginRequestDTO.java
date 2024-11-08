package hoop.api.api.domain.user.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDTO(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6)
        String password
) {
}
