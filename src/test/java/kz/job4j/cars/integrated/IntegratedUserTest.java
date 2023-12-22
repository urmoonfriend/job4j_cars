package kz.job4j.cars.integrated;

import kz.job4j.cars.controller.UserConroller;
import kz.job4j.cars.models.dto.UserDto;
import kz.job4j.cars.models.entity.User;
import kz.job4j.cars.repository.CrudRepository;
import kz.job4j.cars.repository.UserRepository;
import kz.job4j.cars.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IntegratedUserTest {
    @Autowired
    private UserConroller userConroller;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CrudRepository crudRepository;

    private User user1;
    private User user2;
    private UserDto userDto1;
    private UserDto userDto2;

    @BeforeEach
    public void setup() {
        user1 = new User()
                .setLogin("login1")
                .setName("name1")
                .setPassword("password1");

        user2 = new User()
                .setLogin("login2")
                .setName("name2")
                .setPassword("password2");

        userDto1 = new UserDto()
                .setLogin("login1")
                .setName("name1")
                .setPassword("password1");

        userDto2 = new UserDto()
                .setLogin("login2")
                .setName("name2")
                .setPassword("password2");
    }

    @Test
    public void whenCreateThenOk() {
        assertThat(userConroller.create(userDto1)).isEqualTo(ResponseEntity.ok(user1));
    }

    @Test
    public void whenGetAllThenOk() {
        var response = userConroller.getAll();
        List<User> userList = response.getBody();
        assertThat(response).isEqualTo(ResponseEntity.ok(userList));
    }

    @Test
    public void whenGetByIdThenOk() {
        assertThat(userConroller.getById(1)).isEqualTo(ResponseEntity.ok(user1));
    }

    @Test
    public void whenGetByIdThenNotFound() {
        assertThat(userConroller.getById(213456789)).isEqualTo(
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

}
