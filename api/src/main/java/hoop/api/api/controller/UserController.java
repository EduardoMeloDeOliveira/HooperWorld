package hoop.api.api.controller;


import hoop.api.api.domain.user.DTO.UserChangePasswordRequestDTO;
import hoop.api.api.domain.user.DTO.UserRequestDTO;
import hoop.api.api.domain.user.DTO.UserResponseDTO;
import hoop.api.api.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.createUser(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> userChangePassword
            (@PathVariable Long id,
             @RequestBody @Valid UserChangePasswordRequestDTO requestDTO
            ) {
        return ResponseEntity.ok(userService.userChangePassword(id, requestDTO));
    }
}
