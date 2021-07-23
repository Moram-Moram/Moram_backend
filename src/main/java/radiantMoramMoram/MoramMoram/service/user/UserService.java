package radiantMoramMoram.MoramMoram.service.user;

import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.request.mypage.UpdateUserRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPagePostResponse;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPageResponse;
import radiantMoramMoram.MoramMoram.payload.response.token.TokenResponse;

import java.util.List;

public interface UserService {

    void join(SignUpRequest user);

    TokenResponse login(LoginRequest user);

    void deleteUser(String userId);

    void updateUser(UpdateUserRequest updateUserRequest, String token);

    MyPageResponse getMyPage(String userId, String token);

    void duplicateIdCheck(String userId);

    void duplicateNickNameCheck(String userNickName);

}
