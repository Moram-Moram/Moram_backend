package radiantMoramMoram.MoramMoram.controller.comment;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import radiantMoramMoram.MoramMoram.payload.request.comment.WriteCommentRequest;

import javax.servlet.http.HttpServletRequest;

public class CommentController {
    @PostMapping("/post/{postId}")
    public void writeComment(HttpServletRequest request, @PathVariable String postId, @RequestBody WriteCommentRequest commentReq){


    }
}
