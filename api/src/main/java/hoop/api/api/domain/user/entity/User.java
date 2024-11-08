package hoop.api.api.domain.user.entity;

import hoop.api.api.domain.comment.entity.Comment;
import hoop.api.api.domain.like.entity.Like;
import hoop.api.api.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private  List<Like> likes;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private  List<Comment> comments;


    public List<Post>getPosts() {
        return posts == null ? new ArrayList<>() : posts;
    }

    public List<Like> getLikes() {
        return likes == null ? new ArrayList<>() : likes;
    }

    public List<Comment> getComments() {
     return comments == null ? new ArrayList<>() : comments;
    }
}
