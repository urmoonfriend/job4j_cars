package kz.job4j.cars.repository;

import kz.job4j.cars.models.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    Car create(Car car);

    void update(Car car);

    void delete(int carId);

    List<Car> findAllOrderById();

    Optional<Car> findById(int carId);
}
