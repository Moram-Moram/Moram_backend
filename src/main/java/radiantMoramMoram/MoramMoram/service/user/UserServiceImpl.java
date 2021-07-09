package radiantMoramMoram.MoramMoram.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.entity.user.UserBuilder;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.TokenInfoRequest;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;
import radiantMoramMoram.MoramMoram.security.token.TokenDTO;

import static radiantMoramMoram.MoramMoram.entity.user.User.pwEncrypt;


@RequiredArgsConstructor
@Log
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    private final JwtUtil jwtUtil;

    public void join(SignUpRequest userReq){
        User user = new UserBuilder()
                .setId(userReq.getId())
                .setPassword(userReq.getPassword())
                .setNickname(userReq.getNickname())
                .setWhiteCheck(userReq.isWhiteCheck())
                .build();
        userRepository.save(user);
    }

}
