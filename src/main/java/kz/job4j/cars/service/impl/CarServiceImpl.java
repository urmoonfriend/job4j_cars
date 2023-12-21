package kz.job4j.cars.service.impl;

import kz.job4j.cars.models.entity.Car;
import kz.job4j.cars.repository.CarRepository;
import kz.job4j.cars.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public void update(Car car) {
        carRepository.update(car);
    }

    @Override
    public void delete(int carId) {
        carRepository.delete(carId);
    }

    @Override
    public List<Car> findAllOrderById() {
        return carRepository.findAllOrderById();
    }

    @Override
    public Optional<Car> findById(int carId) {
        return carRepository.findById(carId);
    }
}
