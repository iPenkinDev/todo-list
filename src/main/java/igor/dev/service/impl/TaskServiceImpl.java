package igor.dev.service.impl;

import igor.dev.dao.TaskDao;
import igor.dev.domain.Status;
import igor.dev.domain.Task;
import igor.dev.dto.TaskDto;
import igor.dev.service.TaskService;
import igor.dev.util.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;
    private final TaskMapper taskMapper;

    @Override
    public void createTask(TaskDto taskDto) {
        taskDto.setStatus(Status.IN_PROGRESS);
        taskDao.createTask(taskMapper.mapToEntity(taskDto));
    }

    @Override
    public TaskDto getTaskById(int id) {
        return taskMapper.mapToDTO(taskDao.getTaskById(id));
    }


    @Override
    public Page<TaskDto> getAllTasksPages(Pageable pageable) {
        Page<Task> taskPage = taskDao.getAllTasksPages(pageable);
        List<Task> taskList = taskPage.getContent();
        List<TaskDto> taskDtoList = taskList.stream().map(taskMapper::mapToDTO).toList();
        return new PageImpl<>(taskDtoList, pageable, taskPage.getTotalElements());
    }

    @Override
    public void updateTask(TaskDto taskDto) {
       taskDao.updateTask(taskMapper.mapToEntity(taskDto));
    }

    @Override
    public void removeTask(int id) {
        taskDao.removeTask(id);
    }
}