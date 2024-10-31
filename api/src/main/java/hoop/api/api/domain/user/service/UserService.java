package hoop.api.api.domain.user.service;


import hoop.api.api.domain.user.DTO.UserChangePasswordRequestDTO;
import hoop.api.api.domain.user.DTO.UserRequestDTO;
import hoop.api.api.domain.user.DTO.UserResponseDTO;
import hoop.api.api.domain.user.entity.User;
import hoop.api.api.domain.user.mapper.UserMapper;
import hoop.api.api.domain.user.repository.UserRepository;
import hoop.api.api.handler.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public UserResponseDTO createUser(UserRequestDTO requestDTO){

        User user = UserMapper.toEntity(requestDTO);

        userRepository.save(user);

        return UserMapper.toDto(user);

    }

    public UserResponseDTO getUserById(Long id){
        return UserMapper.
                toDto(userRepository
                        .findById(id)
                        .orElseThrow(()-> new ObjectNotFoundException("User não encontrado")));
    }

    public UserResponseDTO userChangePassword(Long id, UserChangePasswordRequestDTO requestDTO){
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("User não econtrado"));


        user.setPassword(requestDTO.password());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }


    public User existsUserById(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("User não encontrado"));
    }




}
