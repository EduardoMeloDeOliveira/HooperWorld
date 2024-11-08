package hoop.api.api.domain.user.mapper;

import hoop.api.api.domain.user.DTO.UserRegisterRequestDTO;
import hoop.api.api.domain.user.DTO.UserResponseDTO;
import hoop.api.api.domain.user.entity.User;

public class UserMapper {


    public static User toEntity(UserRegisterRequestDTO requestDTO){

        return User.builder()
                .id(null)
                .name(requestDTO.name())
                .email(requestDTO.email())
                .password(requestDTO.password())
                .build();
    }

    public static UserResponseDTO toDto(User user){

        return UserResponseDTO.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }


}
