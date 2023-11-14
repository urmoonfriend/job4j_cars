package kz.job4j.cars.repository;

import kz.job4j.cars.models.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    Owner create(Owner owner);

    void update(Owner owner);

    void delete(int ownerId);

    List<Owner> findAllOrderById();

    Optional<Owner> findById(int ownerId);
}
