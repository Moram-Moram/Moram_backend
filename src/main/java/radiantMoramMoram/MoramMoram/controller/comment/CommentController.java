package radiantMoramMoram.MoramMoram.controller.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radiantMoramMoram.MoramMoram.payload.request.comment.WriteCommentRequest;
import radiantMoramMoram.MoramMoram.payload.response.comment.CommentResponse;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;
import radiantMoramMoram.MoramMoram.service.comment.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final JwtUtil jwtUtil;

    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<HttpStatus> writeComment(HttpServletRequest request, @PathVariable int postId, @RequestBody WriteCommentRequest commentReq){
        String userId = jwtUtil.getUserIdFromJwtToken(request.getHeader("Authorization"));
        commentService.writeComment(commentReq, userId, postId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponse> commentList(@PathVariable int postId){
        return commentService.commentList(postId);
    }

}
