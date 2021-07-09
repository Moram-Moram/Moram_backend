package radiantMoramMoram.MoramMoram.service.user;

import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.payload.response.token.TokenResponse;

public interface UserService {
    void join(SignUpRequest user);
    TokenResponse login(LoginRequest user);
}
