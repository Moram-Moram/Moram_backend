package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.payload.request.comment.WriteCommentRequest;
import radiantMoramMoram.MoramMoram.service.comment.CommentService;


@SpringBootTest
public class CommentTests {
    @Autowired
    CommentService commentService;

    @Test
    public void commentInsertTest(){
        String content = "content";
        String userId = "00000";
        int postId =2;
        WriteCommentRequest commentReq = WriteCommentRequest.builder()
                .content(content)
                .build();
        commentService.writeComment(commentReq, userId, postId);
    }
}
