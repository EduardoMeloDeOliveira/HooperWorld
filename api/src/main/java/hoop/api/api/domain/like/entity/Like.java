package hoop.api.api.domain.like.entity;

import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime likedAt;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
