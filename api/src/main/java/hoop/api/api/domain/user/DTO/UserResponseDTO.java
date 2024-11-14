package hoop.api.api.domain.user.DTO;

import lombok.Builder;

@Builder
public record UserResponseDTO (
        Long userId,
        String name,
        String email,
        String imageUrl
){
}
