package radiantMoramMoram.MoramMoram.payload.request.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequest {

    private String id;
    private String pw;

    @Builder
    public LoginRequest(String id, String pw){
        this.id = id;
        this.pw = pw;
    }
}
