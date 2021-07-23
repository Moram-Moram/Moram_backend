package radiantMoramMoram.MoramMoram.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.request.mypage.UpdateUserRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.payload.response.token.AccessTokenResponse;
import radiantMoramMoram.MoramMoram.payload.response.token.TokenResponse;
import radiantMoramMoram.MoramMoram.service.user.UserServiceImpl;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> join(@Validated @RequestBody SignUpRequest user){
        userService.join(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public TokenResponse login(@RequestBody LoginRequest fUser){
        return userService.login(fUser);
    }

    @PutMapping("/token")
    public AccessTokenResponse tokenReissuance(@RequestHeader(name = "Refresh-Token") String token){
        return userService.tokenRefresh(token);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/user/{userId}/update")
    public void updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest,
                           @RequestHeader("Authorization") String token) {
        userService.updateUser(updateUserRequest, token);
    }

    @GetMapping("/user/{userId}/exists")
    public void duplicateIdCheck(@PathVariable String userId){
        userService.duplicateIdCheck(userId);
    }

    @GetMapping("/userNickName/{userNickName}/exists")
    public void duplicateNickNameCheck(@PathVariable String userNickName){
        userService.duplicateNickNameCheck(userNickName);
    }

}
