package ru.malyshev.renderfarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malyshev.renderfarm.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}