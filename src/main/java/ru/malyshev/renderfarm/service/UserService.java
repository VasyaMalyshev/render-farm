package ru.malyshev.renderfarm.service;

import ru.malyshev.renderfarm.dto.UserDto;
import ru.malyshev.renderfarm.entity.User;

public interface UserService {

    User register(UserDto userDto);

    User findByUsername(String username);
}
