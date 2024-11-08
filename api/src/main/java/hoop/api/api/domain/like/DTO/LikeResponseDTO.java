package hoop.api.api.domain.like.DTO;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LikeResponseDTO(

        Long likeId,
        String likedBy,
        LocalDateTime likedAt
) {
}
