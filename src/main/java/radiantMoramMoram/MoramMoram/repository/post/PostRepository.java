package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByReport(boolean report);

}
