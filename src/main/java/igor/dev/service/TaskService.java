package igor.dev.service;

import igor.dev.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);
    TaskDto getTaskById(int id);
    List<TaskDto> getAllTasks();
    TaskDto updateTask(TaskDto taskDto, int id);
    void removeTask(int id);
}
