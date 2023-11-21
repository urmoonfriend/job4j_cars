package kz.job4j.cars.service;

import kz.job4j.cars.mapper.UserMapper;
import kz.job4j.cars.models.dto.UserDto;
import kz.job4j.cars.models.entity.User;
import kz.job4j.cars.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private UserDto userDto1;
    private UserDto userDto2;

    @BeforeEach
    public void setup() {
        user1 = new User()
                .setLogin("login1")
                .setPassword("password1");

        user2 = new User()
                .setLogin("login2")
                .setPassword("password2");

        userDto1 = new UserDto()
                .setLogin("login1")
                .setPassword("password1");

        userDto2 = new UserDto()
                .setLogin("login2")
                .setPassword("password2");
    }

    @Test
    public void whenCreateThenOk() {
        when(userRepository.create(user1)).thenReturn(user1);
        assertThat(userService.create(userDto1)).isEqualTo(user1);
    }

    @Test
    public void whenFindAllOrderByIdThenOk() {
        when(userRepository.findAllOrderById()).thenReturn(List.of(user1, user2));
        assertThat(userService.findAllOrderById()).isEqualTo(List.of(user1, user2));
    }

    @Test
    public void whenFindByIdThenOk() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        assertThat(userService.findById(1)).isEqualTo(Optional.of(user1));
    }

    @Test
    public void whenFindByIdThenNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThat(userService.findById(1)).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindByLikeLoginThenOk() {
        when(userRepository.findByLikeLogin("login")).thenReturn(List.of(user1, user2));
        assertThat(userService.findByLikeLogin("login")).isEqualTo(List.of(user1, user2));
    }

    @Test
    public void whenFindByLoginThenOk() {
        when(userRepository.findByLogin("login1")).thenReturn(Optional.of(user1));
        assertThat(userService.findByLogin("login1")).isEqualTo(Optional.of(user1));
    }

}
