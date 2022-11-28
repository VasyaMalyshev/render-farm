package ru.malyshev.renderfarm.service;

import org.springframework.security.core.Authentication;
import ru.malyshev.renderfarm.dto.TaskDto;
import ru.malyshev.renderfarm.entity.Task;

import java.util.List;

public interface TaskService {

    Task create(TaskDto taskDto, Authentication authentication);

    List<TaskDto> getAll();

    void changeStatus();
}
