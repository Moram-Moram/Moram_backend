package radiantMoramMoram.MoramMoram.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.post.comment.Comment;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.payload.request.comment.WriteCommentRequest;
import radiantMoramMoram.MoramMoram.repository.CommentRepository;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;

// 댓글 작성
// 댓글 보기...? 댓글 리스트 넘기기
// 랜덤 포스트
// 포스트 리스트

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void writeComment(WriteCommentRequest commentReq, String userId, int postId){
        Comment comment = Comment.builder()
                .content(commentReq.getContent())
                .user(userRepository.findById(userId).orElseThrow())
                .post(postRepository.findById(postId).orElseThrow(PostNotFoundException::new))
                .build();

        commentRepository
                .save(comment);
    }
}
