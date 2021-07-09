package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.service.user.UserService;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.entity.user.UserBuilder;


@SpringBootTest(properties = "classpath:/application.yml")
public class UserTests {
    @Autowired
    UserService userService;


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
}
