package radiantMoramMoram.MoramMoram.payload.request.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignInRequest {

    private String id;
    private String password;

}
