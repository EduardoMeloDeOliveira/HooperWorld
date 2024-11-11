package hoop.api.api.domain.post.entity;


import hoop.api.api.domain.comment.entity.Comment;
import hoop.api.api.domain.like.entity.Like;
import hoop.api.api.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Like> likes;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;


    public List<Like>getLikes() {
        return this.likes == null ? new ArrayList<>() : this.likes;
    }


    public List<Comment> getComments() {
        return this.comments == null ? new ArrayList<>() : this.comments;
    }

}
