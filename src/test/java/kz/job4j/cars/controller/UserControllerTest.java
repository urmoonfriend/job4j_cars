package kz.job4j.cars.controller;

import kz.job4j.cars.models.dto.UserDto;
import kz.job4j.cars.models.entity.User;
import kz.job4j.cars.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserConroller userConroller;
    @MockBean
    private UserService userService;
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
        when(userService.create(userDto1)).thenReturn(user1);
        assertThat(userConroller.create(userDto1)).isEqualTo(ResponseEntity.ok(user1));
    }

    @Test
    public void whenGetAllThenOk() {
        when(userService.findAllOrderById()).thenReturn(List.of(user1, user2));
        assertThat(userConroller.getAll()).isEqualTo(ResponseEntity.ok(List.of(user1, user2)));
    }

    @Test
    public void whenGetByIdThenOk() {
        when(userService.findById(1)).thenReturn(Optional.of(user1));
        assertThat(userConroller.getById(1)).isEqualTo(ResponseEntity.ok(user1));
    }

    @Test
    public void whenGetByIdThenNotFound() {
        when(userService.findById(3)).thenReturn(Optional.empty());
        assertThat(userConroller.getById(3)).isEqualTo(
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

}
