package hoop.api.api.controller;


import hoop.api.api.domain.user.DTO.UserChangePasswordRequestDTO;
import hoop.api.api.domain.user.DTO.UserRequestDTO;
import hoop.api.api.domain.user.DTO.UserResponseDTO;
import hoop.api.api.domain.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;



    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.createUser(requestDTO));
    }


    @Operation(summary = "Change user password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password changed successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> userChangePassword
            (@PathVariable Long id,
             @RequestBody @Valid UserChangePasswordRequestDTO requestDTO
            ) {
        return ResponseEntity.ok(userService.userChangePassword(id, requestDTO));
    }
}
