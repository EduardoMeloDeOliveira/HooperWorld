package hoop.api.api.domain.comment.mapper;

import hoop.api.api.domain.comment.DTO.CommentRequestDTO;
import hoop.api.api.domain.comment.DTO.CommentResponseDTO;
import hoop.api.api.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public class CommentMapper {


    public static Comment toEntity(CommentRequestDTO requestDTO){

        return Comment.builder()
                .id(null)
                .content(requestDTO.content())
                .createdAt(LocalDateTime.now())
                .build();


    }

    public static CommentResponseDTO toDTO(Comment comment){
        return CommentResponseDTO.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .author(comment.getUser().getName())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}
