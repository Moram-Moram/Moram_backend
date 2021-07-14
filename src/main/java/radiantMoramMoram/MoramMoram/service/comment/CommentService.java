package radiantMoramMoram.MoramMoram.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import radiantMoramMoram.MoramMoram.entity.post.comment.Comment;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.payload.request.comment.WriteCommentRequest;

import radiantMoramMoram.MoramMoram.payload.response.comment.CommentResponse;
import radiantMoramMoram.MoramMoram.repository.CommentRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;
import radiantMoramMoram.MoramMoram.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

import java.util.List;

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


    @Transactional
    public List<CommentResponse> commentList(int postId){

        List<Comment> comments = commentRepository.findByPost_Id(postId);
        List<CommentResponse> commentList = new ArrayList<>();
        for(Comment c : comments){
            CommentResponse comment = CommentResponse
                    .builder()
                    .content(c.getContent())
                    .userNickName(c.getUser().getNickname())
                    .regDate(c.getRegDate())
                    .build();
            commentList.add(comment);
        }
        return commentList;
    }

}
