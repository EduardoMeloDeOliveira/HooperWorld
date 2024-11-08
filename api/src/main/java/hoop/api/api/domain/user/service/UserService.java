package hoop.api.api.domain.user.service;


import hoop.api.api.domain.user.DTO.UserRegisterRequestDTO;
import hoop.api.api.domain.user.DTO.UserResponseDTO;
import hoop.api.api.domain.user.entity.User;
import hoop.api.api.domain.user.enums.Roles;
import hoop.api.api.domain.user.mapper.UserMapper;
import hoop.api.api.domain.user.repository.UserRepository;
import hoop.api.api.handler.exceptions.ConflitException;
import hoop.api.api.handler.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserResponseDTO register(UserRegisterRequestDTO requestDTO){

        User possibleUser = (User) userRepository.findByEmail(requestDTO.email()).orElse(null);

        if(possibleUser != null){
            throw new ConflitException("User already exists");
        }

        User user = UserMapper.toEntity(requestDTO);
        user.setRole(Roles.USER);
        user.setPassword(passwordEncoder.encode(requestDTO.password()));

        return UserMapper.toDto(userRepository.save(user));

    }

    public UserResponseDTO getUserById(Long id){
        return UserMapper.
                toDto(userRepository
                        .findById(id)
                        .orElseThrow(()-> new ObjectNotFoundException("User não encontrado")));
    }


    public User existsUserById(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("User não encontrado"));
    }




}
