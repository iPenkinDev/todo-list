package igor.dev.service.impl;

import igor.dev.dao.TaskDao;
import igor.dev.domain.Task;
import igor.dev.dto.TaskDto;
import igor.dev.util.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl {

    private final TaskDao taskDao;
    private final TaskMapper taskMapper;


    public TaskDto createTask(TaskDto taskDto) {
       return taskMapper.mapToDTO(taskDao.createTask(taskMapper.mapToEntity(taskDto)));
    }

    public TaskDto getTaskById(int id) {
        return taskMapper.mapToDTO(taskDao.getTaskById(id));
    }

    public List<TaskDto> getAllTasks() {
        return taskDao.getAllTasks().stream().map(taskMapper::mapToDTO).toList();
    }

    public TaskDto updateTask(TaskDto taskDto, int id) {
        Task taskById = taskDao.getTaskById(id);

        taskById.setDescription(taskDto.getDescription());
        taskById.setStatus(taskDto.getStatus());
        return taskMapper.mapToDTO(taskDao.updateTask(taskById));
    }

    public void removeTask(int id) {
        taskDao.removeTask(id);
    }
}