package radiantMoramMoram.MoramMoram.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.entity.user.UserBuilder;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.TokenInfoRequest;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;
import radiantMoramMoram.MoramMoram.security.token.TokenDTO;
import static radiantMoramMoram.MoramMoram.entity.user.User.pwEncrypt;

import javax.persistence.Convert;

@RequiredArgsConstructor
@Log
@Service // applicationService
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public ResponseEntity<String> join(User user){
        if(userRepository.findById(user.getId()).isPresent()) {
            return new ResponseEntity<>("id overlap", HttpStatus.BAD_REQUEST);
        }

        userRepository.save(user);
        return new ResponseEntity<>("create user", HttpStatus.CREATED);
    }
}
