import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.inventorymanagement.entity.AppUser;
import com.example.inventorymanagement.repository.AppUserRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(AppUserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Conversion AppUser -> org.springframework.security.core.userdetails.User
        // Collecte des rôles
        List<GrantedAuthority> authorities = appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .toList();

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}