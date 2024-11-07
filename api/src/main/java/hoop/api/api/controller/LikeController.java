package hoop.api.api.controller;

import hoop.api.api.domain.like.DTO.LikeResponseDTO;
import hoop.api.api.domain.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {


    @Autowired
    private LikeService likeService;


    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<LikeResponseDTO> attachLikeToPost(@PathVariable Long userId, @PathVariable Long postId) {
        return ResponseEntity.ok().body(likeService.attachLikeToPost(userId, postId));
    }


    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<Void> dettachLikeFromPost(@PathVariable Long userId, @PathVariable Long postId) {
        likeService.dettachLikeToPost(userId, postId);

        return ResponseEntity.noContent().build();
    }
}
