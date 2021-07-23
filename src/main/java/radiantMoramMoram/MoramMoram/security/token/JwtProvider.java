package radiantMoramMoram.MoramMoram.security.token;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.exception.InvalidTokenException;
import radiantMoramMoram.MoramMoram.security.auth.AuthDetailService;
import radiantMoramMoram.MoramMoram.security.auth.AuthDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private Long accessTokenExpiration;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    private final AuthDetailService authDetailService;

    public String generateAccessToken(String id) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Authorization");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getSubject();
            return true;
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public String getId(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = authDetailService.loadUserByUsername(getId(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "",authDetails.getAuthorities());
    }
}
