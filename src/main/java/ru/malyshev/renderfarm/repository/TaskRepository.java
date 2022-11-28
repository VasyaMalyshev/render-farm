package ru.malyshev.renderfarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.malyshev.renderfarm.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.taskStatus = 'RENDERING' and t.completeDate < current_timestamp")
    List<Task> findAllNotCompletedStatus();
}
