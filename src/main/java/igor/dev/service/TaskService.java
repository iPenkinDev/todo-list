package igor.dev.service;

import igor.dev.dto.TaskDto;

import java.util.List;

public interface TaskService {

    void createTask(TaskDto taskDto);
    TaskDto getTaskById(int id);
    List<TaskDto> getTasksWithPagination(int pageNumber, int pageSize);
    void updateTask(TaskDto taskDto);
    void removeTask(int id);

}
