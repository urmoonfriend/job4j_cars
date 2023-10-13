package kz.job4j.cars.models.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private Integer id;
    private String login;
    private String password;
}