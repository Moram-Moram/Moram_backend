package radiantMoramMoram.MoramMoram.security.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import radiantMoramMoram.MoramMoram.payload.request.user.TokenInfoRequest;
import radiantMoramMoram.MoramMoram.payload.response.token.TokenResponse;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.service.user.CustomUserDetailService;
import radiantMoramMoram.MoramMoram.util.RedisUtil;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Log
@Component
public class JwtUtil {
    public final static long TOKEN_VALIDATION_SECOND = 60*60*500;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND  = 1000L*60*24*2;

    private final CustomUserDetailService userDetailsService;
    private final RedisUtil redisUtil;

    @Value("${auth.jwt.secret}")
    private String SECRET_KEY;

    public TokenResponse createToken(TokenInfoRequest user){
        return new TokenResponse(generateToken(user),generateRefreshToken(user));
    }

    // create access token
    public String generateToken(TokenInfoRequest user){
        return doGenerateToken(user.getId(), user.getRole(), TOKEN_VALIDATION_SECOND);
    }

    // create refresh token - or user null
    public String generateRefreshToken(TokenInfoRequest user){
        String rToken = doGenerateToken(user.getId(), user.getRole(), REFRESH_TOKEN_VALIDATION_SECOND);
        redisUtil.setData(user.getId(), rToken);
        return rToken;
    }

    private String doGenerateToken(String userId, Authority role, long expireTime){
        Claims claims = Jwts.claims();
        claims.put("user", userId);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey(String secretKey){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY)).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e){
            log.info("잘못된 jwt 서명입니다.");
        } catch (ExpiredJwtException e){
            log.info("만료된 jwt 토큰입니다.");
        } catch (UnsupportedJwtException e){
            log.info("지원되지 않는 jwt 토큰입니다.");
        } catch (IllegalArgumentException e){
            log.info("jwt 토큰이 잘못되었습니다.");
        }
        return false;

    }

    public UserDetails userAuthReturn(String id){
        return userDetailsService.loadUserByUsername(id);
    }


    public String getUserIdFromJwtToken(String accessToken){
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY)).build().parseClaimsJws(accessToken);

        return (String) claims.getBody().get("user");
    }
}
