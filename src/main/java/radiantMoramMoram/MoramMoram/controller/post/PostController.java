package radiantMoramMoram.MoramMoram.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.post.PostResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.PostsResponse;
import radiantMoramMoram.MoramMoram.service.post.PostService;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void writePost(@RequestHeader("Authorization") String token,
                          @RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam("user") String user,
                          @RequestParam("date") LocalDate date,
                          @RequestParam("fileName") MultipartFile[] fileName,
                          @RequestParam("category") String[] category) {

        postService.writePost(
                WritePostRequest.builder()
                        .title(title)
                        .content(content)
                        .user(user)
                        .date(date)
                        .fileName(fileName)
                        .category(category)
                        .build()
                , token);

    };

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Integer postId,
                                @RequestHeader("Authorization") String token) {

        return postService.getPost(postId, token);

    };

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Integer postId,
                           @RequestHeader("Authorization") String token) {

        postService.deletePost(postId, token);

    };

    @PutMapping("/like/{postId}")
    public void likePost(@RequestBody LikePostRequest likePostRequest) {

        postService.likePost(likePostRequest);

    };

    @PutMapping("/report/{postId}")
    public void reportPost(@PathVariable Integer postId,
                           @RequestHeader("Authorization") String token) {

        postService.reportPost(postId, token);

    };

    @GetMapping("/random/{click}")
    public PostResponse randomPost(@PathVariable("click") int click, @RequestHeader(value = "Authorization", required = false) String token){
        return postService.randomPost(click, token);
    }

    @GetMapping("/list/{category}")
    public PostsResponse postList(@PathVariable("category") String category){
        return postService.getPostList(category);
    }
}
