package hoop.api.api.domain.user.repository;

import hoop.api.api.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<UserDetails> findByEmail(String email);
}
