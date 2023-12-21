package kz.job4j.cars.service.impl;

import kz.job4j.cars.mapper.UserMapper;
import kz.job4j.cars.models.dto.UserDto;
import kz.job4j.cars.models.entity.User;
import kz.job4j.cars.repository.UserRepository;
import kz.job4j.cars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User create(UserDto userDto) {
        return userRepository.create(userMapper.getEntityFromDto(userDto));
    }

    @Override
    public Optional<User> create(User user) {
        return Optional.of(userRepository.create(user));
    }

    @Override
    public void update(UserDto userDto) {
        userRepository.update(userMapper.getEntityFromDto(userDto));
    }

    @Override
    public void delete(int userId) {
        userRepository.delete(userId);
    }

    @Override
    public List<User> findAllOrderById() {
        return userRepository.findAllOrderById();
    }

    @Override
    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return userRepository.findByLikeLogin(key);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String login, String password) {
        return userRepository.findByEmailAndPassword(login, password);
    }
}
