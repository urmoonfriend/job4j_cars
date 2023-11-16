package kz.job4j.cars.repository.impl;

import kz.job4j.cars.models.entity.Photo;
import kz.job4j.cars.models.entity.Post;
import kz.job4j.cars.repository.CrudRepository;
import kz.job4j.cars.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernatePhotoRepository implements PhotoRepository {
    private final CrudRepository crudRepository;

    /**
     * Создать фото.
     *
     * @param photo фото
     * @return photo фото.
     */
    @Override
    public Photo create(Photo photo) {
        crudRepository.run(session -> session.persist(photo));
        return photo;
    }

    /**
     * Обновить фото.
     *
     * @param photo фото.
     */
    @Override
    public void update(Photo photo) {
        crudRepository.run(session -> session.persist(photo));
    }

    /**
     * Удалить фото по id.
     *
     * @param photoId ID
     */
    @Override
    public void delete(int photoId) {
        crudRepository.run("delete from Photo where id = :pId",
                Map.of("pId", photoId));
    }

    /**
     * Список фото отсортированных по id.
     *
     * @return список фото.
     */
    @Override
    public List<Photo> findAllOrderById() {
        return crudRepository.query("from Photo order by id asc", Photo.class);
    }

    /**
     * Найти фото по ID
     *
     * @return фото.
     */
    @Override
    public Optional<Photo> findById(int photoId) {
        return crudRepository.optional("from Photo where id = :pId", Photo.class,
                Map.of("pId", photoId));
    }
}
