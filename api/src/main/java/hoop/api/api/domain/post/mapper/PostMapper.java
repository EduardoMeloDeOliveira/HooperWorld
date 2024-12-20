package hoop.api.api.domain.post.mapper;

import hoop.api.api.domain.comment.mapper.CommentMapper;
import hoop.api.api.domain.like.mapper.LikeMapper;
import hoop.api.api.domain.post.DTO.PostRequestDTO;
import hoop.api.api.domain.post.DTO.PostResponseDTO;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.user.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PostMapper {

    public static Post toEnity(PostRequestDTO requestDTO){
        return Post.builder()
                .id(null)
                .createdAt(LocalDateTime.now())
                .content(requestDTO.content())
                .build();
    }

    public static PostResponseDTO toDto(Post post){
        return PostResponseDTO.builder()
                .postId(post.getId())
                .createdAt(post.getCreatedAt())
                .user(UserMapper.toDto(post.getUser()))
                .content(post.getContent())
                .likes(post.getLikes().stream().map(p -> LikeMapper.toDto(p)).collect(Collectors.toList()))
                .comments(post.getComments().stream().map(c -> CommentMapper.toDTO(c)).collect(Collectors.toList()))
                .build();
    }
}
