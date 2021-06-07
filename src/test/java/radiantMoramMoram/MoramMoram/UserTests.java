package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.application.user.UserService;
import radiantMoramMoram.MoramMoram.domain.user.Password;
import radiantMoramMoram.MoramMoram.domain.user.User;
import radiantMoramMoram.MoramMoram.domain.user.UserDTO;


@SpringBootTest(properties = "classpath:/application.yml")
public class UserTests {
    @Autowired
    UserService userService;

    @Test
    public void joinTest(){
        String id = "whddms";
        String pw = "djssl";
        String name  = "이종은";
        Password password = new Password(pw);
        User user = new User(id,password,name);
        userService.join(user);
    }

    @Test
    public void loginTest(){
        String id = "whddms";
        String pw = "djsdl";
        UserDTO user = new UserDTO(id,pw);
        userService.login(user);
    }



}
