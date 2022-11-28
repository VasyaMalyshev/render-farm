package ru.malyshev.renderfarm.dto;

import ru.malyshev.renderfarm.entity.Task;
import ru.malyshev.renderfarm.entity.TaskStatus;

public record StatusHistoryDto(String title, String description, TaskStatus status) {

}
