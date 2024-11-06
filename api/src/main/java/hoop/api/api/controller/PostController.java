package hoop.api.api.controller;

import hoop.api.api.domain.post.DTO.PostRequestDTO;
import hoop.api.api.domain.post.DTO.PostResponseDTO;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{userId}")
    public ResponseEntity<PostResponseDTO> createPost
            (@PathVariable Long userId,
             @RequestBody @Valid PostRequestDTO requestDTO) {
        return ResponseEntity.ok(postService.savePost(userId, requestDTO));
    }

    @PutMapping("/{userId}/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost
            (@PathVariable Long userId,
             @PathVariable Long postId,
             @RequestBody @Valid PostRequestDTO requestDTO) {
        return ResponseEntity.ok(postService.updatePost(userId, postId, requestDTO));
    }

    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long userId, @PathVariable Long postId) {

        postService.deletePost(userId, postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByUserId(@PathVariable Long userId) {

        List<PostResponseDTO> posts = postService.getPostsByUserId(userId);

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(posts);
    }
}
