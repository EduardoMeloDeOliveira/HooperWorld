package hoop.api.api.domain.post.service;

import hoop.api.api.domain.like.entity.Like;
import hoop.api.api.domain.like.service.LikeService;
import hoop.api.api.domain.post.DTO.PostRequestDTO;
import hoop.api.api.domain.post.DTO.PostResponseDTO;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.post.mapper.PostMapper;
import hoop.api.api.domain.post.repository.PostRepository;
import hoop.api.api.domain.user.entity.User;
import hoop.api.api.domain.user.service.UserService;
import hoop.api.api.handler.exceptions.ConflitException;
import hoop.api.api.handler.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    

    public PostResponseDTO savePost(Long userId, PostRequestDTO postRequestDTO) {

        User user = userService.existsUserById(userId);
        Post post = PostMapper.toEnity(postRequestDTO);
        post.setUser(user);

        postRepository.save(post);

        return PostMapper.toDto(post);
    }

    public PostResponseDTO updatePost(Long userId, Long postId, PostRequestDTO postRequestDTO) {

        User user = userService.existsUserById(userId);
        Post post = existsPost(postId);

        if (!post.getUser().getId().equals(userId)) {
            throw new ConflitException("You are not allowed to update this post");
        }
        post.setContent(postRequestDTO.content());
        post.setTitle(postRequestDTO.title());
        postRepository.save(post);

        return PostMapper.toDto(post);

    }


    public void deletePost(Long userId, Long postId) {
        User user = userService.existsUserById(userId);
        Post post = existsPost(postId);

        if (!post.getUser().getId().equals(userId)) {
            throw new ConflitException("You are not allowed to delete this post");
        }


        post.getLikes().clear();

        postRepository.delete(post);
    }



    public List<PostResponseDTO> getPostsByUserId(Long userId) {
        User user = userService.existsUserById(userId);

        List<Post> posts = user.getPosts();
        List<PostResponseDTO> postResponseDTOS = new ArrayList<>();

        if (!posts.isEmpty()) {
            postResponseDTOS = posts.stream().map(post -> PostMapper.toDto(post))
                    .collect(Collectors.toList());
        }

        return postResponseDTOS;
    }


    public PostResponseDTO getPostByPostId(Long postId){

        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found"));

        return PostMapper.toDto(post);

    }

    public Post existsPost(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found"));
    }

    public List<PostResponseDTO> getTop10Posts(){
        List<Post> posts = postRepository.findTop10ByOrderByCreatedAtDesc();

        List<PostResponseDTO> postResponseDTOS = new ArrayList<>();

        return posts.stream().map(p -> PostMapper.toDto(p))
                .collect(Collectors.toList());
    }

}
