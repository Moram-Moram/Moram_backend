package radiantMoramMoram.MoramMoram.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.security.token.TokenDTO;
import radiantMoramMoram.MoramMoram.service.user.UserServiceImpl;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/user")
    public void join(@RequestBody User user){
        userService.join(user);
    }
}
