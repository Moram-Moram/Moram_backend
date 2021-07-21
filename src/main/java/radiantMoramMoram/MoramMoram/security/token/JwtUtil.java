package radiantMoramMoram.MoramMoram.security.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import radiantMoramMoram.MoramMoram.error.TokenErrorCode;
import radiantMoramMoram.MoramMoram.error.TokenException;
import radiantMoramMoram.MoramMoram.exception.InvalidTokenException;
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

    String accessToken = "access";
    String refreshToken = "refresh";

    @Value("${auth.jwt.secret}")
    private String SECRET_KEY;

    public TokenResponse createToken(TokenInfoRequest user){
        return new TokenResponse(generateToken(user),generateRefreshToken(user));
    }

    // create access token
    public String generateToken(TokenInfoRequest user){
        return doGenerateToken(user.getId(), user.getRole(), TOKEN_VALIDATION_SECOND, accessToken);
    }

    // create refresh token - or user null
    public String generateRefreshToken(TokenInfoRequest user){
        String rToken = doGenerateToken(user.getId(), user.getRole(), REFRESH_TOKEN_VALIDATION_SECOND, refreshToken);
        redisUtil.setData(user.getId(), rToken);
        return rToken;
    }

    private String doGenerateToken(String userId, Authority role, long expireTime, String type){
        Claims claims = Jwts.claims();
        claims.put("user", userId);
        claims.put("role", role);
        claims.put("type", type);

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

    public void validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY)).build().parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException e){
            throw new TokenException(TokenErrorCode.INVALID_SIGNATURE);
        } catch (ExpiredJwtException e){
            throw new TokenException(TokenErrorCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e){
            throw new TokenException(TokenErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e){
            throw new TokenException(TokenErrorCode.INVALID_TOKEN);
        }
    }

    public UserDetails userAuthReturn(String id){
        return userDetailsService.loadUserByUsername(id);
    }


    public String getUserIdFromJwtToken(String accessToken){
        return (String) getBodyFromToken(accessToken)
                .get("user");
    }
    public boolean checkTypeFromToken(String token){
        try {
            return getBodyFromToken(token).get("type").equals("refresh");
        } catch (Exception e){
            throw new InvalidTokenException();
        }
    }

    private Claims getBodyFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
