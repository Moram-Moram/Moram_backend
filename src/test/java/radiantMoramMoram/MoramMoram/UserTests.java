package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.entity.role.Role;
import radiantMoramMoram.MoramMoram.entity.role.RoleID;
import radiantMoramMoram.MoramMoram.repository.RoleRepository;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.service.user.UserService;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.entity.user.UserBuilder;


@SpringBootTest(properties = "classpath:/application.yml")
public class UserTests {
    @Autowired
    UserService userService;


    @Test
    public void joinTest(){
        String id = "whddd1110";
        String pw = "djssl";
        String name  = "이종은";
        boolean whiteCheck = true;

        User user = new UserBuilder()
                .setId(id)
                .setPassword(pw)
                .setName(name)
                .setWhiteCheck(whiteCheck)
                .build();

        userService.join(user);
    }
}
