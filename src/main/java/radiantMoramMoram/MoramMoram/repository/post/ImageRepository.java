package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.domain.post.image.Image;
import radiantMoramMoram.MoramMoram.domain.post.image.ImageKey;

public interface ImageRepository extends JpaRepository<Image, ImageKey> {

}
