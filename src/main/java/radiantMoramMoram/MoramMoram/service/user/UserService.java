package radiantMoramMoram.MoramMoram.service.user;

import org.springframework.http.ResponseEntity;
import radiantMoramMoram.MoramMoram.entity.user.User;

public interface UserService {
    ResponseEntity join(User user);
    //TokenDTO login(LoginRequest user);
}
