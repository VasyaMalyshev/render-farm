package ru.malyshev.renderfarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.renderfarm.dto.TaskDto;
import ru.malyshev.renderfarm.entity.Task;
import ru.malyshev.renderfarm.service.StatusHistoryService;
import ru.malyshev.renderfarm.service.TaskService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskDto taskDto, Authentication authentication) {
        taskService.create(taskDto, authentication);
        return new ResponseEntity<>("Создана задача: " + taskDto.title(), HttpStatus.OK);
    }
}