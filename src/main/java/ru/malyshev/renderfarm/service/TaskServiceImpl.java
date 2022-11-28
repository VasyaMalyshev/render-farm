package ru.malyshev.renderfarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.malyshev.renderfarm.dto.TaskDto;
import ru.malyshev.renderfarm.model.StatusHistory;
import ru.malyshev.renderfarm.model.Task;
import ru.malyshev.renderfarm.model.TaskStatus;
import ru.malyshev.renderfarm.model.User;
import ru.malyshev.renderfarm.repository.StatusHistoryRepository;
import ru.malyshev.renderfarm.repository.TaskRepository;
import ru.malyshev.renderfarm.repository.UserRepository;

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

        ///////////////////////// Генерирую случайное время от 1 минуты до 5 ////////////////////
        Date date = new Date();
        int min = 60000; // 1 минута
        int max = 300000; // 5 минут
        int diff = max - min;
        Random random = new Random();
        int randomTime = random.nextInt(diff + 1);
        randomTime += min;
        date.setTime(date.getTime() + randomTime);

        //////////////////////// Создаю Task /////////////////////////////////////////////////////

        User user = userRepository.findById(taskDto.userId()).get();
        List<User> users = new ArrayList<>();
        users.add(user);
        Task task = new Task();
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setTaskStatus(TaskStatus.RENDERING);
        task.setCompleteDate(date);
        task.setUsers(users);
        taskRepository.save(task);
        log.info("IN create_task - task: {} successfully registered", task);
        return task;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findByTitle(String title) {
        return null;
    }

    @Scheduled(fixedRate = 60000)
    @Async
    public void changeStatus() {
        List<Task> result = taskRepository.findAll();
        Date date = new Date();
        for (Task task : result) {
            if ((date.after(task.getCompleteDate()) && task.getTaskStatus() != TaskStatus.COMPLETE)) {
                StatusHistory statusHistory = new StatusHistory();
                statusHistory.setTask(task);
                statusHistory.setTaskStatus(TaskStatus.COMPLETE);
                statusHistoryRepository.save(statusHistory);
                task.setTaskStatus(TaskStatus.COMPLETE);
                taskRepository.save(task);
            }
        }
    }
}
