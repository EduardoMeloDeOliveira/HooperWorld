package hoop.api.api.domain.post.mapper;

import hoop.api.api.domain.post.DTO.PostRequestDTO;
import hoop.api.api.domain.post.DTO.PostResponseDTO;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.user.mapper.UserMapper;

public class PostMapper {

    public static Post toEnity(PostRequestDTO requestDTO){
        return Post.builder()
                .id(null)
                .title(requestDTO.title())
                .content(requestDTO.content())
                .build();
    }

    public static PostResponseDTO toDto(Post post){
        return PostResponseDTO.builder()
                .user(UserMapper.toDto(post.getUser()))
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
