package radiantMoramMoram.MoramMoram.payload.request.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpRequest {
    private String id;
    private String password;
    private String nickname;
    private boolean whiteCheck;
}
