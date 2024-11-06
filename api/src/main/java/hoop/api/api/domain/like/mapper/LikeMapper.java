package hoop.api.api.domain.like.mapper;

import hoop.api.api.domain.like.DTO.LikeResponseDTO;
import hoop.api.api.domain.like.entity.Like;

public class LikeMapper {


    public static LikeResponseDTO toDto (Like like) {

        return LikeResponseDTO.builder()
                .likeId(like.getId())
                .likedAt(like.getLikedAt())
                .likedBy(like.getUser().getName())
                .build();
    }

}
