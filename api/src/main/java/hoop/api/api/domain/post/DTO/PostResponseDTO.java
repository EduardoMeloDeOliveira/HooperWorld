package hoop.api.api.domain.post.DTO;

import hoop.api.api.domain.comment.DTO.CommentResponseDTO;
import hoop.api.api.domain.like.DTO.LikeResponseDTO;
import hoop.api.api.domain.user.DTO.UserResponseDTO;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostResponseDTO(Long postId,
                              UserResponseDTO user,
                              LocalDateTime createdAt,
                              String title,
                              String content,
                              List<LikeResponseDTO> likes,
                              List<CommentResponseDTO> comments) {
}
