package radiantMoramMoram.MoramMoram.payload.request.user;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
public class SignUpRequest {
    @Length(min = 1,max = 12)
    private String id;
    private String password;
    @Length(max = 10)
    private String nickname;
    private boolean whiteCheck;
}
