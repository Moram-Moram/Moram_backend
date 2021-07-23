package radiantMoramMoram.MoramMoram.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.entity.user.UserBuilder;
import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;
import radiantMoramMoram.MoramMoram.error.TokenErrorCode;
import radiantMoramMoram.MoramMoram.error.TokenException;
import radiantMoramMoram.MoramMoram.exception.UserAlreadyExistsException;
import radiantMoramMoram.MoramMoram.exception.UserNotFoundException;
import radiantMoramMoram.MoramMoram.payload.request.mypage.UpdateUserRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.LoginRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.SignUpRequest;
import radiantMoramMoram.MoramMoram.payload.request.user.TokenInfoRequest;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPageResponse;
import radiantMoramMoram.MoramMoram.payload.response.token.AccessTokenResponse;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;
import radiantMoramMoram.MoramMoram.payload.response.token.TokenResponse;

import static radiantMoramMoram.MoramMoram.entity.user.User.pwEncrypt;


@RequiredArgsConstructor
@Log
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void join(SignUpRequest userReq){
        User user = new UserBuilder()
                .setId(userReq.getId())
                .setPassword(userReq.getPassword())
                .setNickname(userReq.getNickname())
                .setWhiteCheck(userReq.isWhiteCheck())
                .build();
        userRepository.save(user);
    }

    @Override
    public TokenResponse login(LoginRequest loginUser){
        User user = userRepository.findByIdAndPassword(loginUser.getId(), pwEncrypt(loginUser.getPw()));

        if(user==null){
            throw new BasicException(ErrorCode.USER_NOT_FOUND);
        }

        TokenInfoRequest tokenInfoReq = TokenInfoRequest.builder()
                .role(user.getRole())
                .id(user.getId())
                .build();

        return jwtUtil.createToken(tokenInfoReq);
    }

    public AccessTokenResponse tokenRefresh(String token){
        if(!jwtUtil.checkTypeFromToken(token)){
            throw new TokenException(TokenErrorCode.INVALID_TOKEN);
        }
        return new AccessTokenResponse(jwtUtil.reissuanceAccessToken(token));
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest, User user) {
        String password = updateUserRequest.getPassword();

        boolean checkBox = updateUserRequest.isCheckBox();

        userRepository.save(user.update(password, checkBox));
    }

    @Override
    public MyPageResponse getMyPage(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return MyPageResponse.builder()
                .id(user.getId())
                .name(user.getNickname())
                .role(user.getRole().toString())
                .build();
    }

    @Override
    public void duplicateIdCheck(String userId) {
        userRepository.findById(userId)
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });
    }

    @Override
    public void duplicateNickNameCheck(String userNickName) {
        userRepository.findByNickname(userNickName)
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException();
                });
    }

}
