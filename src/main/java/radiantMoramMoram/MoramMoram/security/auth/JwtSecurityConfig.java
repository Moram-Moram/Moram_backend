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
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/like/*").hasAnyRole("WATER_DROP", "SCOTCH_MIST", "DRIZZLE", "SHOWER")
                .antMatchers(HttpMethod.GET, "/user/*", "/user/*/update").hasAnyRole("WATER_DROP", "SCOTCH_MIST", "DRIZZLE", "SHOWER")
                .antMatchers(HttpMethod.POST, "/comment/*", "/post", "/post/*").hasAnyRole("WATER_DROP", "SCOTCH_MIST", "DRIZZLE", "SHOWER")
                .antMatchers(HttpMethod.DELETE, "/post/*", "/user/*").hasAnyRole("WATER_DROP", "SCOTCH_MIST", "DRIZZLE", "SHOWER")
                .antMatchers("/admin/report/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().disable();
    }
}
