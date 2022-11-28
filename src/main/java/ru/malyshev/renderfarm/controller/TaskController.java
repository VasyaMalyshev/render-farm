package ru.malyshev.renderfarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.renderfarm.dto.TaskDto;
import ru.malyshev.renderfarm.service.StatusHistoryService;
import ru.malyshev.renderfarm.service.TaskService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final StatusHistoryService statusHistoryService;

    @PostMapping("/create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        taskService.create(taskDto);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @GetMapping("/get-all-tasks")
    public ResponseEntity<?> getAllTasks() {
       return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/status-history")
    public ResponseEntity<?> getAllHistoryStatus() {
        return new ResponseEntity<>(statusHistoryService.getAll(), HttpStatus.OK);
    }

}