package hoop.api.api.controller;

import hoop.api.api.domain.post.DTO.PostRequestDTO;
import hoop.api.api.domain.post.DTO.PostResponseDTO;
import hoop.api.api.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Create a new post for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post created successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<PostResponseDTO> createPost
            (@PathVariable Long userId,
             @RequestBody @Valid PostRequestDTO requestDTO) {
        return ResponseEntity.ok(postService.savePost(userId, requestDTO));
    }


    @Operation(summary = "Update post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully"),
            @ApiResponse(responseCode = "404", description = "Post or User not found")
    })
    @PutMapping("/{userId}/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost
            (@PathVariable Long userId,
             @PathVariable Long postId,
             @RequestBody @Valid PostRequestDTO requestDTO) {
        return ResponseEntity.ok(postService.updatePost(userId, postId, requestDTO));
    }

    @Operation(summary = "Delete post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post or User not found")
    })
    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long userId, @PathVariable Long postId) {

        postService.deletePost(userId, postId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Get all posts by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of user's posts"),
            @ApiResponse(responseCode = "204", description = "No posts found for user")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByUserId(@PathVariable Long userId) {

        List<PostResponseDTO> posts = postService.getPostsByUserId(userId);

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(posts);
    }
}
