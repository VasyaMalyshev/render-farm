package ru.malyshev.renderfarm.dto;

import org.springframework.security.core.Authentication;
import ru.malyshev.renderfarm.entity.TaskStatus;

public record TaskDto(String title, String description, TaskStatus taskStatus) {

}