package radiantMoramMoram.MoramMoram.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.admin.Admin;
import radiantMoramMoram.MoramMoram.exception.AuthorizationFailureException;
import radiantMoramMoram.MoramMoram.payload.request.admin.AdminSignInRequest;
import radiantMoramMoram.MoramMoram.payload.response.admin.AdminSignInResponse;
import radiantMoramMoram.MoramMoram.repository.admin.AdminRepositpry;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.security.token.JwtProvider;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Value("${auth.jwt.exp.access}")
    private Long accessExp;

    private final AdminRepositpry adminRepositpry;
    private final JwtProvider jwtProvider;

    @Override
    public AdminSignInResponse adminSignIn(AdminSignInRequest adminSignInRequest) {
        Admin admin = adminRepositpry.findById(adminSignInRequest.getId())
                .orElseThrow(AuthorizationFailureException::new);

        return AdminSignInResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(adminSignInRequest.getId()))
                .tokenType("access")
                .accessTokenExp(accessExp)
                .role(Authority.ADMIN.toString())
                .build();
    }
}
