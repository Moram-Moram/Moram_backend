package radiantMoramMoram.MoramMoram.payload.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import radiantMoramMoram.MoramMoram.security.auth.Authority;

@Getter
@Builder
@AllArgsConstructor
public class AdminSignInResponse {

    private final String accessToken;

    private final String tokenType;

    private final Long accessTokenExp;

    private final Authority role;

}
