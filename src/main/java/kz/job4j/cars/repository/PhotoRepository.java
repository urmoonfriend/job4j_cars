package kz.job4j.cars.repository;

import kz.job4j.cars.models.entity.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository {
    Photo create(Photo photo);

    void update(Photo photo);

    void delete(int photoId);

    List<Photo> findAllOrderById();

    Optional<Photo> findById(int photoId);
}
