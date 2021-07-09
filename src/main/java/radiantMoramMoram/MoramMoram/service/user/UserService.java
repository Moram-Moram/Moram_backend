package radiantMoramMoram.MoramMoram.service.user;

import org.springframework.http.ResponseEntity;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.security.token.TokenDTO;

public interface UserService {
    void join(SignUpRequest user);
    TokenDTO login(LoginRequest user);
}
