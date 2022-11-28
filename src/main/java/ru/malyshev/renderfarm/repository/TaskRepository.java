package ru.malyshev.renderfarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malyshev.renderfarm.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
