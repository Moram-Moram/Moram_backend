package radiantMoramMoram.MoramMoram.application.user;

import org.springframework.http.ResponseEntity;
import radiantMoramMoram.MoramMoram.domain.user.User;
import radiantMoramMoram.MoramMoram.domain.user.UserDTO;

public interface UserService {
    ResponseEntity join(User user);
    ResponseEntity login(UserDTO user);
}
