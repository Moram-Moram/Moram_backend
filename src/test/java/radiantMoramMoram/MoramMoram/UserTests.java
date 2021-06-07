package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.application.user.UserService;
import radiantMoramMoram.MoramMoram.domain.user.User;


@SpringBootTest(properties = "classpath:/application.yml")
public class UserTests {
    @Autowired
    UserService userService;

    @Test
    public void join(){
        System.out.println("run");
        User user = new User("id00","pw","name");
        userService.join(user);
    }



}
