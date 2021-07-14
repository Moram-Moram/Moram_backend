package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.service.user.UserService;
import radiantMoramMoram.MoramMoram.entity.user.User;

import java.util.Optional;


@SpringBootTest(properties = "classpath:/application.yml")
public class UserTests {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void joinTest(){
        String id = "00000";
        String pw = "djssl";
        String name  = "이종은";
        boolean whiteCheck = true;

        SignUpRequest signUpReq = SignUpRequest.builder()
                .id(id)
                .password(pw)
                .nickname(name)
                .whiteCheck(whiteCheck)
                .build();

        userService.join(signUpReq);
    }

    @Test
    public void roleUp(){
        Optional<User> user = userRepository.findById("00000");
        user.get().setRole(Authority.ADMIN);
        userRepository.save(user.get());
    }
}
