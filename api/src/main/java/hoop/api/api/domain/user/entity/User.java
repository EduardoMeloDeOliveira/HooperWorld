package hoop.api.api.domain.user.entity;

import hoop.api.api.domain.comment.entity.Comment;
import hoop.api.api.domain.like.entity.Like;
import hoop.api.api.domain.post.entity.Post;
import hoop.api.api.domain.user.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String imageUrl;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private  List<Like> likes;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private  List<Comment> comments;


    @Enumerated(EnumType.STRING)
    private Roles role;


    public List<Post>getPosts() {
        return posts == null ? new ArrayList<>() : posts;
    }

    public List<Like> getLikes() {
        return likes == null ? new ArrayList<>() : likes;
    }

    public List<Comment> getComments() {
     return comments == null ? new ArrayList<>() : comments;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
    return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
