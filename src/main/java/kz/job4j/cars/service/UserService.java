package kz.job4j.cars.service;

import kz.job4j.cars.models.dto.UserDto;
import kz.job4j.cars.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(UserDto user);

    void update(UserDto user);

    void delete(int userId);

    List<User> findAllOrderById();

    Optional<User> findById(int userId);

    List<User> findByLikeLogin(String key);

    Optional<User> findByLogin(String login);
}
