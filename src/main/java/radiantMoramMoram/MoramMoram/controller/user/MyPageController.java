package radiantMoramMoram.MoramMoram.controller.user;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPagePostResponse;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPageResponse;
import radiantMoramMoram.MoramMoram.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MyPageController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public MyPageResponse getMyPage(@PathVariable String userId) {
        return userService.getMyPage(userId);
    }

    @GetMapping("/{userId}/mine")
    public List<MyPagePostResponse> getMyPost(@PathVariable User user, int postId) {
        return userService.getMyPagePost(user, postId);
    }
    
    @GetMapping("/{userId}/like")
    public List<MyPagePostResponse> getLikePost(@PathVariable User userId, int postId) {
        return userService.getLikePost(userId, postId);
    }

}
