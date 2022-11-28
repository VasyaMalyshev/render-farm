package ru.malyshev.renderfarm.service;

import ru.malyshev.renderfarm.dto.TaskDto;
import ru.malyshev.renderfarm.model.Task;

import java.util.List;

public interface TaskService {

    Task create(TaskDto taskDto);

    List<Task> getAll();

    Task findByTitle(String title);

    void changeStatus();
}
