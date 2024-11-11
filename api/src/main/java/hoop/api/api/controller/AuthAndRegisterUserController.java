package hoop.api.api.controller;

import hoop.api.api.domain.user.DTO.UserLoginRequestDTO;
import hoop.api.api.domain.user.DTO.UserRegisterRequestDTO;
import hoop.api.api.domain.user.DTO.UserResponseDTO;
import hoop.api.api.domain.user.entity.User;
import hoop.api.api.domain.user.service.UserService;
import hoop.api.api.infra.security.DTO.JwtDTO;
import hoop.api.api.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthAndRegisterUserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


    @Operation(summary = "Registrar um novo usuário", description = "Registra um novo usuário com as informações fornecidas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRegisterRequestDTO requestDTO){
        return ResponseEntity.ok(userService.register(requestDTO));
    }



    @Operation(summary = "Realizar login", description = "Autentica o usuário e retorna um token JWT.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> doLogin(@RequestBody @Valid UserLoginRequestDTO requestDTO){

      var extractTokenFromFormLogin = new UsernamePasswordAuthenticationToken(requestDTO.email(),requestDTO.password());

      var auth = authenticationManager.authenticate(extractTokenFromFormLogin);

      var user = (User) auth.getPrincipal();


      var jwtToken =  tokenService.generateToken(user);

      return ResponseEntity.ok(new JwtDTO(jwtToken));
    }

}
