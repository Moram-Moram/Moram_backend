package radiantMoramMoram.MoramMoram.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.post.GetPostResponse;
import radiantMoramMoram.MoramMoram.service.post.PostService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void writePost(@RequestBody WritePostRequest writePostRequest,
                          @RequestHeader("Authorization") String token) {

        postService.writePost(writePostRequest, token);

    };

    @GetMapping("/{postId}")
    public GetPostResponse getPost(@RequestBody Integer postId,
                                   @RequestHeader("Authorization") String token) {

        return postService.getPost(postId, token);

    };

    @DeleteMapping("/{postId}")
    public void deletePost(@RequestBody Integer postId) {

        postService.deletePost(postId);

    };

    @PutMapping("/like/{postId}")
    public void likePost(@RequestBody LikePostRequest likePostRequest) {

        postService.likePost(likePostRequest);

    };

    @PutMapping("/report/{postId}")
    public void reportPost(@RequestBody ReportPostRequest reportPostRequest) {

        postService.reportPost(reportPostRequest);

    };

    @GetMapping("/random/{click}")
    public GetPostResponse randomPost(@PathVariable("click") int click){
        return postService.randomPost(click);
    }

    @GetMapping("/list/{category}")
    public PostsResponse postList(@PathVariable("category") String category){
        return postService.getPostList(category);
    }
}
