package hoop.api.api.controller;

import hoop.api.api.domain.like.DTO.LikeResponseDTO;
import hoop.api.api.domain.like.service.LikeService;
import hoop.api.api.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {


    @Autowired
    private LikeService likeService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/{postId}")
    @Operation
            (summary = "Adicionar curtida",
                    description = "Adiciona uma curtida ao post especificado pelo usuário.")

    public ResponseEntity<LikeResponseDTO> attachLikeToPost(@RequestHeader("Authorization")String token, @PathVariable Long postId) {
        Long userId = tokenService.getUserIdFromToken(token);

        return ResponseEntity.ok().body(likeService.attachLikeToPost(userId, postId));
    }


    @DeleteMapping("/{postId}")
    @Operation(summary = "Remover curtida",
            description = "Remove uma curtida do post especificado pelo usuário.")

    public ResponseEntity<Void> dettachLikeFromPost(@RequestHeader("Authorization") String token, @PathVariable Long likeId) {

        Long userId = tokenService.getUserIdFromToken(token);

        likeService.dettachLikeToPost(userId, likeId);

        return ResponseEntity.noContent().build();
    }
}
