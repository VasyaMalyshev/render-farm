package ru.malyshev.renderfarm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.malyshev.renderfarm.dto.TaskDto;
import ru.malyshev.renderfarm.entity.StatusHistory;
import ru.malyshev.renderfarm.entity.Task;
import ru.malyshev.renderfarm.entity.TaskStatus;
import ru.malyshev.renderfarm.entity.User;
import ru.malyshev.renderfarm.exception_handling.RenderFarmException;
import ru.malyshev.renderfarm.repository.StatusHistoryRepository;
import ru.malyshev.renderfarm.repository.TaskRepository;
import ru.malyshev.renderfarm.repository.UserRepository;
import ru.malyshev.renderfarm.security.jwt.JwtUser;
import ru.malyshev.renderfarm.service.TaskService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    @Override
    public Task create(TaskDto taskDto) {
        JwtUser jwtUser = (JwtUser) taskDto.auth().getPrincipal();
        User user = userRepository.findById(jwtUser.getId()).get();
        List<User> users = new ArrayList<>();
        users.add(user);
        Task task = new Task();
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setTaskStatus(TaskStatus.RENDERING);
        task.setCompleteDate(randomTime());
        task.setUsers(users);
        taskRepository.save(task);
        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setTask(task);
        statusHistory.setTaskStatus(task.getTaskStatus());
        statusHistoryRepository.save(statusHistory);
        log.info("IN create_task - task: {} successfully registered", task);
        return task;
    }

    @Override
    public List<Task> getAll() {
        if (taskRepository.findAll().isEmpty()) {
            throw new RenderFarmException("Список задач пуст");
        }
        return taskRepository.findAll();
    }

    @Scheduled(fixedRate = 60000)
    public void changeStatus() {
        List<Task> result = taskRepository.findAllNotCompletedStatus();

        for (Task task : result) {
            task.setTaskStatus(TaskStatus.COMPLETE);
            taskRepository.save(task);
            StatusHistory statusHistory = new StatusHistory();
            statusHistory.setTask(task);
            statusHistory.setTaskStatus(task.getTaskStatus());
            statusHistoryRepository.save(statusHistory);
        }
    }

    private Date randomTime() {
        Date date = new Date();
        int min = 60000; // 1 минута
        int max = 300000; // 5 минут
        int diff = max - min;
        Random random = new Random();
        int randomTime = random.nextInt(diff + 1);
        randomTime += min;
        date.setTime(date.getTime() + randomTime);
        return date;
    }
}
