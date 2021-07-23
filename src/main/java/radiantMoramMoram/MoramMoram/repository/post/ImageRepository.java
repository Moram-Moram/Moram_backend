package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.entity.post.image.ImageKey;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, ImageKey> {

    List<Image> findByPostOrderById(Post post);

    Optional<Post> findByPostId(Integer postId);

    void deleteByPostId(Integer postId);

}
