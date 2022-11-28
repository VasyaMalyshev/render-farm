package ru.malyshev.renderfarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.renderfarm.dto.AuthDto;
import ru.malyshev.renderfarm.dto.UserDto;
import ru.malyshev.renderfarm.service.AuthService;
import ru.malyshev.renderfarm.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    //////////////////////////////// Аутентификация пользователя /////////////////////////////////////////////////
    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody AuthDto authDto) {
        return ResponseEntity.ok(authService.signin(authDto));
    }

    //////////////////////////////// Регистрация нового пользователя /////////////////////////////////////////////
    @PostMapping(value = "register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        userService.register(userDto);
        return new ResponseEntity<>("Создан новый пользователь под ником: " + userDto, HttpStatus.OK);
    }
}