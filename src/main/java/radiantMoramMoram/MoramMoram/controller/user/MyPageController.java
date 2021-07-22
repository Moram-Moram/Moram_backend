package radiantMoramMoram.MoramMoram.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPageResponse;
import radiantMoramMoram.MoramMoram.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{userId}")
public class MyPageController {

    private UserService userService;

    @GetMapping
    public MyPageResponse getMyPage(@PathVariable String userId) {
        return userService.getMyPage(userId);
    }
}
