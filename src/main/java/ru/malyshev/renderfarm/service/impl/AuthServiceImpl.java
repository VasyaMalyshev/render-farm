package ru.malyshev.renderfarm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.malyshev.renderfarm.dto.AuthDto;
import ru.malyshev.renderfarm.dto.TokenDto;
import ru.malyshev.renderfarm.entity.User;
import ru.malyshev.renderfarm.security.jwt.JwtTokenProvider;
import ru.malyshev.renderfarm.service.AuthService;
import ru.malyshev.renderfarm.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenDto signin(AuthDto authDto) {

        String username = authDto.username();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password()));
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + authDto.username() + " not found");
        }
        TokenDto tokenDto = new TokenDto(jwtTokenProvider.createToken(username, user.getRoles()));
        return tokenDto;
    }
}
