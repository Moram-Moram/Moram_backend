package radiantMoramMoram.MoramMoram.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.GetPostResponse;
import radiantMoramMoram.MoramMoram.service.post.PostService;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @PostMapping
    public void writePost(@RequestBody WritePostRequest writePostRequest) {

        postService.writePost(writePostRequest);

    };

    @GetMapping
    public GetPostResponse getPost(@RequestBody Integer postId) {

        return postService.getPost(postId);

    };

    @DeleteMapping
    public void deletePost(@RequestBody Integer postId) {

        postService.deletePost(postId);

    };

    @PutMapping
    public void likePost(@RequestBody LikePostRequest likePostRequest) {

        postService.likePost(likePostRequest);

    };

    @PutMapping
    public void reportPost(@RequestBody ReportPostRequest reportPostRequest) {

        postService.reportPost(reportPostRequest);

    };

}
