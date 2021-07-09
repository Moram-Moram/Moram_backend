package radiantMoramMoram.MoramMoram.payload.request.user;

import lombok.Builder;
import lombok.Getter;
import radiantMoramMoram.MoramMoram.security.auth.Authority;

import java.util.List;

@Builder
@Getter
public class TokenInfoRequest {
    private String id;
    private Authority role;
}
