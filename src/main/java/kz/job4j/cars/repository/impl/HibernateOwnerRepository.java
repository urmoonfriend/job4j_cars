package kz.job4j.cars.repository.impl;

import kz.job4j.cars.models.entity.Owner;
import kz.job4j.cars.repository.CrudRepository;
import kz.job4j.cars.repository.DatabaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateOwnerRepository implements DatabaseRepository<Owner> {
    private final CrudRepository crudRepository;

    /**
     * Создать в базе владельца авто.
     *
     * @param owner владелец авто на создание
     * @return owner владелец авто.
     */
    @Override
    public Owner create(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
        return owner;
    }

    /**
     * Обновить в базе владельца авто.
     *
     * @param owner владелец авто.
     */
    @Override
    public void update(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
    }

    /**
     * Удалить владельца авто по id.
     *
     * @param ownerId ID
     */
    @Override
    public void delete(int ownerId) {
        crudRepository.run("delete from Owner where id = :oId",
                Map.of("oId", ownerId));
    }

    /**
     * владельцев авто отсортированных по id.
     *
     * @return владельцев авто.
     */
    @Override
    public List<Owner> findAllOrderById() {
        return crudRepository.query("from Owner order by id asc", Owner.class);
    }

    /**
     * Найти владельца авто по ID
     *
     * @return владелец авто.
     */
    @Override
    public Optional<Owner> findById(int ownerId) {
        return crudRepository.optional("from Owner where id = :oId", Owner.class,
                Map.of("oId", ownerId));
    }
}
