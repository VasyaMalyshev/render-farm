package ru.malyshev.renderfarm.service;

import ru.malyshev.renderfarm.model.User;

public interface UserService {

    User register(User user);

    User findByUsername(String username);
}
