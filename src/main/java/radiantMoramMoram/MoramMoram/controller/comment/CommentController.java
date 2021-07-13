package radiantMoramMoram.MoramMoram.controller.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import radiantMoramMoram.MoramMoram.payload.request.comment.WriteCommentRequest;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;
import radiantMoramMoram.MoramMoram.service.comment.CommentService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final JwtUtil jwtUtil;

    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public void writeComment(HttpServletRequest request, @PathVariable int postId, @RequestBody WriteCommentRequest commentReq){
        System.out.println("성공");
        String userId = jwtUtil.getUserIdFromJwtToken(request.getHeader("Authorization"));
        commentService.writeComment(commentReq, userId, postId);
    }
}
