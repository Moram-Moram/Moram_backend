package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.entity.post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
