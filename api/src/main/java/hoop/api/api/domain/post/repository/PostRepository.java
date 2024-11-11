    package hoop.api.api.domain.post.repository;

    import hoop.api.api.domain.post.entity.Post;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface PostRepository extends JpaRepository<Post,Long> {
        List<Post> findTop10ByOrderByCreatedAtDesc();

    }
