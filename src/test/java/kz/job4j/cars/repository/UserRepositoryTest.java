package kz.job4j.cars.repository;

import kz.job4j.cars.models.dto.UserDto;
import kz.job4j.cars.models.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {
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
    public void whenFindByIdThenNotFound() {
        var foundUser = userRepository.findById(0);
        assertThat(foundUser).isEqualTo(Optional.empty());
    }

    @Test
    public void whenCreateThenOk() {
        var createdUser = userRepository.create(user1);
        var foundUser = userRepository.findById(createdUser.getId());
        assertThat(createdUser).isEqualTo(foundUser.get());
    }

    @Test
    public void whenDeleteThenOk() {
        var createdUser = userRepository.create(user2);
        var foundUser = userRepository.findById(createdUser.getId());
        userRepository.delete(foundUser.get().getId());
        var foundUser2 = userRepository.findById(createdUser.getId());
        assertThat(foundUser2).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindAllOrderByIdThenOk() {
        var createdUser = userRepository.create(user1);
        List<User> userList = userRepository.findAllOrderById();
        assertThat(userList.size()).isGreaterThan(0);
    }

    @Test
    public void whenFindByLikeLoginThenOk() {
        List<User> userList = userRepository.findByLikeLogin(user1.getLogin());
        assertThat(userList.size()).isGreaterThan(0);
    }

    @Test
    public void whenFindByLoginThenNotFound() {
        Optional<User> userOpt = userRepository.findByLogin("someLogin");
        assertThat(userOpt.isPresent()).isEqualTo(false);
    }

    @Test
    public void whenFindByLoginThenOk() {
        userRepository.findByLikeLogin(user2.getLogin()).forEach(user -> userRepository.delete(user.getId()));
        var createdUser = userRepository.create(user2);
        Optional<User> userOpt = userRepository.findByLogin(createdUser.getLogin());
        assertThat(userOpt.isPresent()).isEqualTo(true);
    }

}
