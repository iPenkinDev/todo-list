package igor.dev.service.impl;

import igor.dev.dao.TaskDao;
import igor.dev.domain.Status;
import igor.dev.domain.Task;
import igor.dev.dto.TaskDto;
import igor.dev.service.TaskService;
import igor.dev.util.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;
    private final TaskMapper taskMapper;


    public void createTask(TaskDto taskDto) {
        taskDto.setStatus(Status.IN_PROGRESS);
        taskDao.createTask(taskMapper.mapToEntity(taskDto));
    }

    public TaskDto getTaskById(int id) {
        return taskMapper.mapToDTO(taskDao.getTaskById(id));
    }

    public List<TaskDto> getTasksWithPagination(int pageNumber, int pageSize) {
        List<Task> tasks = taskDao.getTasksWithPagination(pageNumber, pageSize);
        return tasks.stream()
                .map(taskMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public void updateTask(TaskDto taskDto) {
       taskDao.updateTask(taskMapper.mapToEntity(taskDto));
    }

    public void removeTask(int id) {
        taskDao.removeTask(id);
    }
}