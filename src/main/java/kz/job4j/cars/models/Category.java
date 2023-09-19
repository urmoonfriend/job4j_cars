package kz.job4j.cars.models;

import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded  = true)
public class Category {
    @NonNull
    @Getter
    @EqualsAndHashCode.Include
    private int id;
    @Getter
    @Setter
    private String name;

    public Category(final @NonNull int id) {
        this.id = id;
    }
}
