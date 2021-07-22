package radiantMoramMoram.MoramMoram.service.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.exception.UserNotFoundException;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPageResponse;
import radiantMoramMoram.MoramMoram.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private UserRepository userRepository;

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
}
