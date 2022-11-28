package ru.malyshev.renderfarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malyshev.renderfarm.model.StatusHistory;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {
}
