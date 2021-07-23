package radiantMoramMoram.MoramMoram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByIdAndPassword(String id, String pw);

    Optional<User> findByNickname(String nickName);
}
