package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, ImageKey> {

    List<Image> findByPostOrderById(Integer postId);

}
