package ru.malyshev.renderfarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.renderfarm.dto.AuthDto;
import ru.malyshev.renderfarm.dto.UserDto;
import ru.malyshev.renderfarm.service.AuthService;
import ru.malyshev.renderfarm.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    //////////////////////////////// Аутентификация пользователя /////////////////////////////////////////////////
    @PostMapping(value = "signin")
    public ResponseEntity<?> signin(@RequestBody AuthDto authDto) {
        try {
            return ResponseEntity.ok(authService.signin(authDto));
        } catch (Exception e) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.BAD_REQUEST);
        }
    }

    //////////////////////////////// Регистрация нового пользователя /////////////////////////////////////////////
    @PostMapping(value = "signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        try {
            userService.register(userDto);
            return new ResponseEntity<>("Создан новый пользователь под ником: " + userDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка создания нового пользователя", HttpStatus.BAD_REQUEST);
        }
    }
}