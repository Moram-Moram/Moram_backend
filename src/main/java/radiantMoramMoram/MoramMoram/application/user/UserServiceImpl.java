package radiantMoramMoram.MoramMoram.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.domain.user.Password;
import radiantMoramMoram.MoramMoram.domain.user.User;
import radiantMoramMoram.MoramMoram.domain.user.UserDTO;
import radiantMoramMoram.MoramMoram.domain.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity join(User user){
        if(userRepository.findById(user.getId()).isPresent()){
            return new ResponseEntity("id overlap", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity("create user", HttpStatus.OK);
    }

    public ResponseEntity login(UserDTO loginUser){
        Password password = new Password(loginUser.getPw());
        User user = userRepository.findByIdAndPassword(loginUser.getId(), password);

        if(user==null){
            System.out.println("user null");
            return new ResponseEntity("user not found", HttpStatus.NOT_FOUND);
        }

        System.out.println("user 있음");
        return new ResponseEntity(HttpStatus.OK);
    }

}
