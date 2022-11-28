package ru.malyshev.renderfarm.service;

import ru.malyshev.renderfarm.dto.TaskDto;
import ru.malyshev.renderfarm.entity.Task;

import java.util.List;

public interface TaskService {

    Task create(TaskDto taskDto);

    List<Task> getAll();

    void changeStatus();
}
