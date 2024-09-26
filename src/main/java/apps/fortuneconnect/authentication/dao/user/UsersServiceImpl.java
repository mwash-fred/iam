package apps.fortuneconnect.authentication.dao.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UserDetailsService {
    private final UserRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByEmailAndDeletedFlag(username, false)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
        return UserDetailsImpl.build(user);
    }
}
