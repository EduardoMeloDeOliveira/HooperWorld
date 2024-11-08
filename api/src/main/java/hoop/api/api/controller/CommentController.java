package hoop.api.api.controller;

import hoop.api.api.domain.comment.DTO.CommentRequestDTO;
import hoop.api.api.domain.comment.DTO.CommentResponseDTO;
import hoop.api.api.domain.comment.repository.CommentRepository;
import hoop.api.api.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping("/{userId}/{postId}")
    @Operation(summary = "Adicionar comentário", description = "Adiciona um comentário ao post especificado pelo usuário.")
    public ResponseEntity<CommentResponseDTO> attachCommentToPost
            (@PathVariable Long userId,
             @PathVariable Long postId,
             @RequestBody @Valid CommentRequestDTO requestDTO) {

        return ResponseEntity.ok(commentService.attachComentToPost(userId, postId, requestDTO));
    }

    @Operation(summary = "Atualizar comentário", description = "Atualiza um comentário específico do usuário.")
    @PutMapping("/{userId}/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment
            (@PathVariable Long userId,
             @PathVariable Long commentId,
             @RequestBody @Valid CommentRequestDTO requestDTO) {
        return ResponseEntity.ok(commentService.updateComment(userId, commentId, requestDTO));
    }

    @DeleteMapping("/{userId}/{commentId}")
    @Operation(summary = "Deletar comentário", description = "Remove um comentário específico de um post pelo usuário.")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.noContent().build();
    }

}
