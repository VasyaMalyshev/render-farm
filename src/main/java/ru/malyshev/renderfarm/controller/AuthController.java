package ru.malyshev.renderfarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.renderfarm.dto.AuthDto;
import ru.malyshev.renderfarm.dto.TokenDto;
import ru.malyshev.renderfarm.model.User;
import ru.malyshev.renderfarm.security.jwt.JwtTokenProvider;
import ru.malyshev.renderfarm.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping(value = "authenticate")
    public ResponseEntity<?> login(@RequestBody AuthDto requestDto) {
        try {
            String username = requestDto.username();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.username(), requestDto.password()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            TokenDto tokenDto = new TokenDto(jwtTokenProvider.createToken(username, user.getRoles()));

            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "addNewUser")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        try {
            User user2 = userService.findByUsername(user.getUsername());
            if (user2 != null) {
                return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
            }
            userService.register(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка создания нового пользователя", HttpStatus.BAD_REQUEST);
        }
    }
}