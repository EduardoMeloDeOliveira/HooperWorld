package hoop.api.api.controller;

import hoop.api.api.domain.like.DTO.LikeResponseDTO;
import hoop.api.api.domain.like.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {


    @Autowired
    private LikeService likeService;


    @PostMapping("/{userId}/{postId}")
    @Operation
            (summary = "Adicionar curtida",
                    description = "Adiciona uma curtida ao post especificado pelo usuário.")

    public ResponseEntity<LikeResponseDTO> attachLikeToPost(@PathVariable Long userId, @PathVariable Long postId) {
        return ResponseEntity.ok().body(likeService.attachLikeToPost(userId, postId));
    }


    @DeleteMapping("/{userId}/{postId}")
    @Operation(summary = "Remover curtida",
            description = "Remove uma curtida do post especificado pelo usuário.")

    public ResponseEntity<Void> dettachLikeFromPost(@PathVariable Long userId, @PathVariable Long postId) {
        likeService.dettachLikeToPost(userId, postId);

        return ResponseEntity.noContent().build();
    }
}
