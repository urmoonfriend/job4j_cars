package kz.job4j.cars.repository.impl;

import kz.job4j.cars.models.entity.Car;
import kz.job4j.cars.repository.CarRepository;
import kz.job4j.cars.repository.CrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateCarRepository implements CarRepository {
    private final CrudRepository crudRepository;

    /**
     * Создать в базе авто.
     * @param car авто на создание
     * @return car авто.
     */
    @Override
    public Car create(Car car) {
        crudRepository.run(session -> session.persist(car));
        return car;
    }

    /**
     * Обновить в базе авто.
     *
     * @param car авто.
     */
    @Override
    public void update(Car car) {
        crudRepository.run(session -> session.persist(car));
    }

    /**
     * Удалить авто по id.
     *
     * @param carId ID
     */
    @Override
    public void delete(int carId) {
        crudRepository.run("delete from Car where id = :cId",
                Map.of("cId", carId));
    }

    /**
     * Список авто отсортированных по id.
     *
     * @return список авто.
     */
    @Override
    public List<Car> findAllOrderById() {
        return crudRepository.query("from Car order by id asc", Car.class);
    }

    /**
     * Найти авто по ID
     *
     * @return авто.
     */
    @Override
    public Optional<Car> findById(int carId) {
        return crudRepository.optional("from Car where id = :cId", Car.class,
                Map.of("cId", carId));
    }
}
