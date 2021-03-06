package radiantMoramMoram.MoramMoram.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Log
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findById(username);
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        if(user.isPresent()){
            auth.add(new SimpleGrantedAuthority(user.get().getRole().toString()));
        } else{
            throw new UsernameNotFoundException("User not found");
        }

        log.info("UserDetailsService getRole: "+user.get().getRole().toString());

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.get().getId())
                .password(user.get().getPassword())
                .roles(user.get().getRole().toString())
                .build();

    }
}
