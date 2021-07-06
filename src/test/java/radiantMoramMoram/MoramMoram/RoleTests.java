package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.entity.role.Role;
import radiantMoramMoram.MoramMoram.entity.role.RoleID;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.entity.user.UserBuilder;
import radiantMoramMoram.MoramMoram.repository.RoleRepository;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.service.user.UserService;

@SpringBootTest(properties = "classpath:/application.yml")
public class RoleTests {
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void roleInsertTest(){
        String id = "whddd111";
        String pw = "djssl";
        String name  = "이종은";

        User user = new UserBuilder()
                .setId(id)
                .setPassword(pw)
                .setName(name)
                .build();
        userService.join(user);

        RoleID roleID = new RoleID();
        roleID.setRole(Authority.ROLE_SCOTCH_MIST);
        roleID.setUserId(user);
        Role role = new Role();
        role.setRoleID(roleID);

        roleRepository.save(role);

    }
}
