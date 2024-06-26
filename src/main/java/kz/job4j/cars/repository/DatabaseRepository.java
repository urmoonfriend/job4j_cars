package kz.job4j.cars.repository;

import java.util.List;
import java.util.Optional;

public interface DatabaseRepository<T> {
    T create(T entity);

    void update(T entity);

    void delete(int id);

    List<T> findAllOrderById();

    Optional<T> findById(int id);
}
