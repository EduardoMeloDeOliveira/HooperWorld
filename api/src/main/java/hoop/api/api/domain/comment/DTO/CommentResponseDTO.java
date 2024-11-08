package hoop.api.api.domain.comment.DTO;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentResponseDTO (
        Long commentId,
        String content,
        String author,
        LocalDateTime createdAt
){
}
