package kz.job4j.cars.controller;

import kz.job4j.cars.models.dto.UserDto;
import kz.job4j.cars.models.entity.User;
import kz.job4j.cars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserConroller {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @PutMapping()
    public void update(@RequestBody UserDto userDto) {
        userService.update(userDto);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAllOrderById());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getAll(@PathVariable("userId") Integer userId) {
        Optional<User> userOpt = userService.findById(userId);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(null));
    }
}
