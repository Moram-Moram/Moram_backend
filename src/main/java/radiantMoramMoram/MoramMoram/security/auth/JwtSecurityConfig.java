package radiantMoramMoram.MoramMoram.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import radiantMoramMoram.MoramMoram.security.token.JwtFilter;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUtil jwtUtil;

    @Override
    public void configure(HttpSecurity http) throws  Exception {
        http
                .cors().and()
                .csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class) //
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/post/*").hasAnyRole()
                .antMatchers(HttpMethod.POST, "/comment/*").hasAnyRole("WATER_DROP", "SCOTCH_MIST", "DRIZZLE", "SHOWER")
                .antMatchers("/auth", "/user", "/token", "/post/").permitAll()
                .antMatchers("/admin/report/**").hasRole("ADMIN")
                .antMatchers("/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable();
    }
}
