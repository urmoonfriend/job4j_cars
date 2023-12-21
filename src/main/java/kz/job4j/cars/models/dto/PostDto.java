package kz.job4j.cars.models.dto;

import kz.job4j.cars.models.entity.Car;
import kz.job4j.cars.models.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
public class PostDto {
    private Integer id;
    private String title;
    private String description;
    private Car car;
    private Photo photo;
    private boolean sold;
}
