package radiantMoramMoram.MoramMoram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByIdAndPassword(String id, Password pw);
}
