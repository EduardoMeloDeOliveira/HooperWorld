package hoop.api.api.domain.like.repository;

import hoop.api.api.domain.like.entity.Like;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Boolean existsByUserAndPost(User user, Post post);
    Optional<Like> findByUserAndPost(User user, Post post);

}
