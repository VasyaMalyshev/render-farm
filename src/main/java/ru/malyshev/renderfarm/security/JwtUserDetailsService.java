package ru.malyshev.renderfarm.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.malyshev.renderfarm.model.User;
import ru.malyshev.renderfarm.security.jwt.JwtUser;
import ru.malyshev.renderfarm.security.jwt.JwtUserFactory;
import ru.malyshev.renderfarm.service.UserService;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    @Lazy
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}