package hoop.api.api.controller;

import hoop.api.api.domain.like.DTO.LikeResponseDTO;
import hoop.api.api.domain.like.service.LikeService;
import hoop.api.api.infra.security.TokenService;
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
    public ResponseEntity<LikeResponseDTO> toggleLike
            (@RequestHeader("Authorization") String token, @PathVariable Long postId) {
        Long userId = tokenService.getUserIdFromToken(token);
        return  ResponseEntity.ok(likeService.toggleLike(userId, postId));

    }

}
