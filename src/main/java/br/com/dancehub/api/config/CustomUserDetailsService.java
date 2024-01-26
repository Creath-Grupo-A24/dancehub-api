package br.com.dancehub.api.config;

import br.com.dancehub.api.user.User;
import br.com.dancehub.api.user.UserRepository;
import br.com.dancehub.api.utils.UUIDUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public User getByUUID(String uuid) {
        return userRepository.findById(UUIDUtils.getFromString(uuid)).orElseThrow(() -> new UsernameNotFoundException("Token is not valid"));
    }
}
