package hoop.api.api.controller;

import hoop.api.api.domain.user.DTO.UserResponseDTO;
import hoop.api.api.domain.user.entity.User;
import hoop.api.api.domain.user.service.UserService;
import hoop.api.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;



    @GetMapping()
    public ResponseEntity<UserResponseDTO> getUserById(@RequestHeader("Authorization") String token){
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/user-id")
    public ResponseEntity<Long> getUserIdFromToken(@RequestHeader("Authorization") String token){
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.ok(userService.userId(userId));
    }


    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadUserImage(@RequestHeader("Authorization") String token, @RequestParam("file") MultipartFile file) {
        Long userId = tokenService.getUserIdFromToken(token);
        String imageUrl = userService.uploadUserImage(userId, file);
        return ResponseEntity.ok(imageUrl);
    }

    @GetMapping("/image")
    public ResponseEntity<?> getUserImage(@RequestHeader("Authorization") String token) {

        Long userId = tokenService.getUserIdFromToken(token);
        try {
            User user = userService.existsUserById(userId);
            Path imagePath = Paths.get(user.getImageUrl());
            Resource imageResource = new org.springframework.core.io.UrlResource(imagePath.toUri());
            if (imageResource.exists() && imageResource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return new ResponseEntity<>(imageResource, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
