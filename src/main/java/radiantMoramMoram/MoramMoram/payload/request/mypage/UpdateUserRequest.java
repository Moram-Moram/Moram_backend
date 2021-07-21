package radiantMoramMoram.MoramMoram.payload.request.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String password;

    private String newPassword;

    private boolean checkBox;

}
