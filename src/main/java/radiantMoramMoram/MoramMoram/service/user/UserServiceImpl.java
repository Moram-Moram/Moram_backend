package radiantMoramMoram.MoramMoram.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.entity.user.UserBuilder;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;

@RequiredArgsConstructor
@Log
@Service // applicationService
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public ResponseEntity<String> join(User user){
        if(userRepository.findById(user.getId()).isPresent()) {
            return new ResponseEntity<>("id overlap", HttpStatus.BAD_REQUEST);
        }
        User saveUser = new UserBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setWhiteCheck(user.isWhiteCheck())
                .build();

        userRepository.save(saveUser);
        return new ResponseEntity<>("create user", HttpStatus.CREATED);
    }
}
