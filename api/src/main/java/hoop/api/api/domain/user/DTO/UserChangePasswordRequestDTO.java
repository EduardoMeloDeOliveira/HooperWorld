package hoop.api.api.domain.user.DTO;

import jakarta.validation.constraints.NotBlank;

public record UserChangePasswordRequestDTO(

        @NotBlank
        String password
) {
}
