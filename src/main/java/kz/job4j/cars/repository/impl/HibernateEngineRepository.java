package kz.job4j.cars.repository.impl;

import kz.job4j.cars.models.entity.Engine;
import kz.job4j.cars.repository.CrudRepository;
import kz.job4j.cars.repository.DatabaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateEngineRepository implements DatabaseRepository<Engine> {
    private final CrudRepository crudRepository;

    /**
     * Создать в базе двигатель.
     * @param engine двигатель на создание
     * @return engine двигатель.
     */
    @Override
    public Engine create(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return engine;
    }

    /**
     * Обновить в двигатель авто.
     *
     * @param engine двигатель.
     */
    @Override
    public void update(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
    }

    /**
     * Удалить двигатель по id.
     *
     * @param engineId ID
     */
    @Override
    public void delete(int engineId) {
        crudRepository.run("delete from Engine where id = :eId",
                Map.of("eId", engineId));
    }

    /**
     * Список двигателей отсортированных по id.
     *
     * @return список двигателей.
     */
    @Override
    public List<Engine> findAllOrderById() {
        return crudRepository.query("from Engine order by id asc", Engine.class);
    }

    /**
     * Найти двигатель по ID
     *
     * @return двигатель.
     */
    @Override
    public Optional<Engine> findById(int engineId) {
        return crudRepository.optional("from Engine where id = :eId", Engine.class,
                Map.of("eId", engineId));
    }
}
