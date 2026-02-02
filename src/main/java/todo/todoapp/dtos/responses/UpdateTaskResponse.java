package todo.todoapp.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class UpdateTaskResponse {
    private String id;
    private String title;
    private String description;
}
