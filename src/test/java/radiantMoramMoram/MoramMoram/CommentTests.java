package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.request.comment.WriteCommentRequest;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;
import radiantMoramMoram.MoramMoram.service.comment.CommentService;
import radiantMoramMoram.MoramMoram.service.post.PostServiceImpl;

import java.util.Optional;

@SpringBootTest
public class CommentTests {
    @Autowired
    CommentService commentService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void commentInsertTest(){
        WriteCommentRequest req = WriteCommentRequest.builder()
                .content("진짜 짱이다")
                .build();
        Optional<Post> post = postRepository.findById(2);
        Optional<User> user = userRepository.findById("00000");

        if(post.isPresent() && user.isPresent()){
            commentService.writeComment(req, post.get(), user.get());
        }

    }
}
