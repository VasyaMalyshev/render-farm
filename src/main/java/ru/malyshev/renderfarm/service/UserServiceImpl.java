package ru.malyshev.renderfarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.malyshev.renderfarm.model.Role;
import ru.malyshev.renderfarm.model.User;
import ru.malyshev.renderfarm.model.UserStatus;
import ru.malyshev.renderfarm.repository.RoleRepository;
import ru.malyshev.renderfarm.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(UserStatus.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - {} username founded", result);
        return result;
    }

}