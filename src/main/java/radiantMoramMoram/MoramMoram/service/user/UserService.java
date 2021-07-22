package radiantMoramMoram.MoramMoram.service.user;

import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.request.mypage.UpdateUserRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPageResponse;
import radiantMoramMoram.MoramMoram.payload.response.token.TokenResponse;

public interface UserService {

    void join(SignUpRequest user);

    TokenResponse login(LoginRequest user);

    void deleteUser(String userId);

    void updateUser(UpdateUserRequest updateUserRequest, User user);

    MyPageResponse getMyPage(String userId);

    void duplicateCheck(SignUpRequest signUpRequest);

}
