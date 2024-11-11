package hoop.api.api.controller;

import hoop.api.api.domain.user.DTO.UserResponseDTO;
import hoop.api.api.domain.user.service.UserService;
import hoop.api.api.infra.security.AuthService;
import hoop.api.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
