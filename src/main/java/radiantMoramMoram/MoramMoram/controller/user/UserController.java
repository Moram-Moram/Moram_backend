package radiantMoramMoram.MoramMoram.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.payload.response.token.TokenResponse;
import radiantMoramMoram.MoramMoram.service.user.UserServiceImpl;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/user")
    public void join(@RequestBody SignUpRequest user){
        userService.join(user);
    }

    @PostMapping("/auth")
    public TokenResponse login(@RequestBody LoginRequest fUser){
        return userService.login(fUser);
    }
}
