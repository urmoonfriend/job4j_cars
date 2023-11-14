package kz.job4j.cars.repository;

import kz.job4j.cars.models.entity.HistoryOwner;

import java.util.List;
import java.util.Optional;

public interface HistoryOwnerRepository {
    HistoryOwner create(HistoryOwner historyOwner);

    void update(HistoryOwner historyOwner);

    void delete(int historyOwnerId);

    List<HistoryOwner> findAllOrderById();

    Optional<HistoryOwner> findById(int historyOwnerId);
}
