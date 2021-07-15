package radiantMoramMoram.MoramMoram.security.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import radiantMoramMoram.MoramMoram.exception.ExpiredTokenException;
import radiantMoramMoram.MoramMoram.exception.InvalidTokenException;
import radiantMoramMoram.MoramMoram.payload.request.user.TokenInfoRequest;
import radiantMoramMoram.MoramMoram.payload.response.token.TokenResponse;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.util.RedisUtil;

import java.security.Key;
import java.util.Date;

@Log
@Component
public class JwtUtil {
    private static final String AUTHORITIES_KEY = "auth";

    public final static long TOKEN_VALIDATION_SECOND = 1000L*10;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND  = 1000L*60*24*2;

    @Autowired
    RedisUtil redisUtil;



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

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();


        return jwt;
    }

    public String parseToken(String token) throws ExpiredJwtException {
        String result;
        try {
            result = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody().getSubject();
            if(!Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody().get("type").equals("access_token"))
                throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException();
        }
        return token;
    }

    private Key getSigningKey(String secretKey){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
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
}
