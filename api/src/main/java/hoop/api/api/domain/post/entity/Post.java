package hoop.api.api.domain.post.entity;


import hoop.api.api.domain.like.entity.Like;
import hoop.api.api.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();

}
