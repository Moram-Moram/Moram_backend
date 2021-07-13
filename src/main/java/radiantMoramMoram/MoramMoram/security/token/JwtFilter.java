package radiantMoramMoram.MoramMoram.security.token;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("filter 진입");
        String accessToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(accessToken) && jwtUtil.validateToken(accessToken)){
            String userId = jwtUtil.getUserIdFromJwtToken(accessToken);
            UserDetails userDetails = jwtUtil.userAuthReturn(userId);


            System.out.println(userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities()));
        }
        filterChain.doFilter(request, response);
    }
}
