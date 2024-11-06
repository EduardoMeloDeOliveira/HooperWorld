package hoop.api.api.domain.post.DTO;

import hoop.api.api.domain.user.DTO.UserResponseDTO;
import lombok.Builder;

@Builder
public record PostResponseDTO (
       UserResponseDTO user,
        String title,
        String content
){
}
