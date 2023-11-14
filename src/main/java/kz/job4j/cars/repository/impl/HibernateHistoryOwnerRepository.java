package kz.job4j.cars.repository.impl;

import kz.job4j.cars.models.entity.HistoryOwner;
import kz.job4j.cars.repository.CrudRepository;
import kz.job4j.cars.repository.HistoryOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateHistoryOwnerRepository implements HistoryOwnerRepository {
    private final CrudRepository crudRepository;

    /**
     * Создать историю о владении авто.
     * @param historyOwner история владения авто
     * @return история владения авто.
     */
    @Override
    public HistoryOwner create(HistoryOwner historyOwner) {
        crudRepository.run(session -> session.persist(historyOwner));
        return historyOwner;
    }

    /**
     * Обновить в базе история владения авто.
     *
     * @param historyOwner история владения авто.
     */
    @Override
    public void update(HistoryOwner historyOwner) {
        crudRepository.run(session -> session.persist(historyOwner));
    }

    /**
     * Удалить историю владения авто по id.
     *
     * @param historyOwnerId ID
     */
    @Override
    public void delete(int historyOwnerId) {
        crudRepository.run("delete from HistoryOwner where id = :historyOwnerId",
                Map.of("historyOwnerId", historyOwnerId));
    }

    /**
     * Список историю владения авто отсортированных по id.
     *
     * @return список истории владения авто.
     */
    @Override
    public List<HistoryOwner> findAllOrderById() {
        return crudRepository.query("from HistoryOwner order by id asc", HistoryOwner.class);
    }

    /**
     * Найти историю владения авто по ID
     *
     * @return история владения авто.
     */
    @Override
    public Optional<HistoryOwner> findById(int historyOwnerId) {
        return crudRepository.optional("from HistoryOwner where id = :historyOwnerId", HistoryOwner.class,
                Map.of("historyOwnerId", historyOwnerId));
    }

}
