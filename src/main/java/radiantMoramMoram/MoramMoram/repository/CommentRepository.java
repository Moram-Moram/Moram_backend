package radiantMoramMoram.MoramMoram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.entity.post.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
