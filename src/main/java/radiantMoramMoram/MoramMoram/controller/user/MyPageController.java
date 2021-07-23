package radiantMoramMoram.MoramMoram.controller.user;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPagePostResponse;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPageResponse;
import radiantMoramMoram.MoramMoram.service.post.PostService;
import radiantMoramMoram.MoramMoram.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MyPageController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping("/{userId}")
    public MyPageResponse getMyPage(@PathVariable String userId) {
        return userService.getMyPage(userId);
    }

    @GetMapping("/{userId}/mine")
    public List<MyPagePostResponse> getMyPost(@PathVariable String userId) {
        return postService.getMyPagePost(userId);
    }
    
    @GetMapping("/{userId}/like")
    public List<MyPagePostResponse> getLikePost(@PathVariable String userId) {
        return postService.getLikePost(userId);
    }

}
