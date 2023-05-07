package igor.dev.dto;

import igor.dev.domain.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TaskDto {

    private Integer id;

    @NotEmpty(message = "Description should not be empty")
    private String description;

    @NotEmpty(message = "Status should not be empty")
    private Status status;
}
