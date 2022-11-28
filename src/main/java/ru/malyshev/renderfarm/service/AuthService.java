package ru.malyshev.renderfarm.service;

import ru.malyshev.renderfarm.dto.AuthDto;
import ru.malyshev.renderfarm.dto.TokenDto;

public interface AuthService {

    TokenDto signin(AuthDto authDto);
}
