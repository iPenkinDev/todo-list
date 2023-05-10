package igor.dev.service;

import igor.dev.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    void createTask(TaskDto taskDto);
    TaskDto getTaskById(int id);
    Page<TaskDto> getAllTasksPages(Pageable pageable);
    void updateTask(TaskDto taskDto);
    void removeTask(int id);

}
