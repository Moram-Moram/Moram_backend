package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.entity.post.image.ImageKey;

import javax.transaction.Transactional;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, ImageKey> {

    List<Image> findByPostOrderById(Post post);
    @Transactional
    void deleteByPostId(int postId);

}
