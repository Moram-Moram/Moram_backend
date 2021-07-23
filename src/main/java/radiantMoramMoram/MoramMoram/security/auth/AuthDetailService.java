package radiantMoramMoram.MoramMoram.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.exception.UserNotFoundException;
import radiantMoramMoram.MoramMoram.repository.admin.AdminRepositpry;

@Service
@RequiredArgsConstructor
public class AuthDetailService implements UserDetailsService {

    private final AdminRepositpry adminRepositpry;

    @Override
    public AuthDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return adminRepositpry.findById(userId)
                .map(AuthDetails::new)
                .orElseThrow(UserNotFoundException::new);
    }
}
