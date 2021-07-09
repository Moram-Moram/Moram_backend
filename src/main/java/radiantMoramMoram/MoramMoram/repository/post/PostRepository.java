package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.entity.post.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

    void deleteByPostId(Integer postId);

}
