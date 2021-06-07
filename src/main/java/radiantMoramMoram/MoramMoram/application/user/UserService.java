package radiantMoramMoram.MoramMoram.application.user;

import org.springframework.http.ResponseEntity;
import radiantMoramMoram.MoramMoram.domain.user.User;

public interface UserService {
    ResponseEntity join(User user);
}
