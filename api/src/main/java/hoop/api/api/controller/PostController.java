package hoop.api.api.controller;

import hoop.api.api.domain.post.DTO.PostRequestDTO;
import hoop.api.api.domain.post.DTO.PostResponseDTO;
import hoop.api.api.domain.post.service.PostService;
import hoop.api.api.infra.security.TokenService;
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

    @Autowired
    private TokenService tokenService;


    @Operation(summary = "Create a new post for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post created successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping()
    public ResponseEntity<PostResponseDTO> createPost
            (@RequestHeader("Authorization")String token,
             @RequestBody @Valid PostRequestDTO requestDTO) {
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.ok(postService.savePost(userId, requestDTO));
    }


    @Operation(summary = "Update post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully"),
            @ApiResponse(responseCode = "404", description = "Post or User not found")
    })
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost
            (@RequestHeader("Authorization")String token,
             @PathVariable Long postId,
             @RequestBody @Valid PostRequestDTO requestDTO) {
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.ok(postService.updatePost(userId, postId, requestDTO));
    }

    @Operation(summary = "Delete post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post or User not found")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@RequestHeader("Authorization")String token, @PathVariable Long postId) {

        Long userId = tokenService.getUserIdFromToken(token);
        postService.deletePost(userId, postId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Get all posts by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of user's posts"),
            @ApiResponse(responseCode = "204", description = "No posts found for user")
    })
    @GetMapping("/get-post-by-user")
    public ResponseEntity<List<PostResponseDTO>> getPostsByUserId(@RequestHeader("Authorization")String token) {

        Long userId = tokenService.getUserIdFromToken(token);

        List<PostResponseDTO> posts = postService.getPostsByUserId(userId);

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(posts);
    }


    @Operation(summary = "Obter post por ID", description = "Retorna os detalhes de um post específico pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Post encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Post não encontrado")
    @GetMapping("/get-by-post-id/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostByPostId(postId));
    }


    @GetMapping("/get-top-ten")
    public ResponseEntity<List<PostResponseDTO>> getTopTenPosts(){
        List<PostResponseDTO> posts = postService.getTop10Posts();

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(posts);
    }
}
