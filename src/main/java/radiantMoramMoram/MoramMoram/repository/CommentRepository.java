package radiantMoramMoram.MoramMoram.repository;

import org.springframework.data.repository.CrudRepository;
import radiantMoramMoram.MoramMoram.entity.post.comment.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByPost_Id(int postId);
}
