package hoop.api.api.domain.like.service;

import hoop.api.api.domain.like.DTO.LikeResponseDTO;
import hoop.api.api.domain.like.entity.Like;
import hoop.api.api.domain.like.mapper.LikeMapper;
import hoop.api.api.domain.like.repository.LikeRepository;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.post.service.PostService;
import hoop.api.api.domain.user.entity.User;
import hoop.api.api.domain.user.service.UserService;
import hoop.api.api.handler.exceptions.ConflitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    public LikeResponseDTO attachLikeToPost(Long userId, Long postId) {
        User user = userService.existsUserById(userId);
        Post post = postService.existsPost(postId);

        if (likeRepository.existsByUserAndPost(user, post)) {
            throw new ConflitException("User already liked this post");
        }

        Like like = Like.builder()
                .id(null)
                .likedAt(LocalDateTime.now())
                .user(user)
                .post(post)
                .build();

        likeRepository.save(like);
        return LikeMapper.toDto(like);
    }




    public void dettachLikeToPost(Long userId, Long likeId) {

        User user = userService.existsUserById(userId);
        Like like = likeRepository.findById(likeId).orElseThrow(()-> new ConflitException("Like not found"));

        if(!user.getId().equals(like.getUser().getId())) {

            throw new ConflitException("Like not detached");
        }

        likeRepository.delete(like);

    }
}
