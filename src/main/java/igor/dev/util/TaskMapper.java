package igor.dev.util;

import igor.dev.domain.Task;
import igor.dev.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ModelMapper modelMapper;

    public Task mapToEntity(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

    public TaskDto mapToDTO(Task newTask) {
        return modelMapper.map(newTask, TaskDto.class);
    }
}