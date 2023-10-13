package kz.job4j.cars.mapper;

import kz.job4j.cars.models.dto.UserDto;
import kz.job4j.cars.models.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto getModelFromEntity(User user);

    @InheritInverseConfiguration
    User getEntityFromDto(UserDto userDto);
}
