package igor.dev.dto;

import igor.dev.domain.Status;
import lombok.Data;

@Data
public class TaskDto {

    private Integer id;

    private String description;

    private Status status;
}
