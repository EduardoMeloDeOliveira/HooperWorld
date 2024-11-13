package hoop.api.api.domain.like.service;

import hoop.api.api.domain.like.DTO.LikeResponseDTO;
import hoop.api.api.domain.like.entity.Like;
import hoop.api.api.domain.like.mapper.LikeMapper;
import hoop.api.api.domain.like.repository.LikeRepository;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.post.service.PostService;
import hoop.api.api.domain.user.entity.User;
import hoop.api.api.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    public LikeResponseDTO toggleLike(Long userId, Long postId) {
        User user = userService.existsUserById(userId);
        Post post = postService.existsPost(postId);
        Optional<Like> likeOpt = likeRepository.findByUserAndPost(user, post);


       if(likeOpt.isPresent()){
           likeRepository.delete(likeOpt.get());
           return null;
       }

           Like like = Like.builder()
                   .id(null)
                   .post(post)
                   .user(user)
                   .likedAt(LocalDateTime.now())
                   .build();

           likeRepository.save(like);

           return LikeMapper.toDto(like);
    }
}
