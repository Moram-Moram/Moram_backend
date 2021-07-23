package radiantMoramMoram.MoramMoram.payload.request.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @NotEmpty
    private String password;

    private boolean whiteCheck;

}
