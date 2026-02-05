package todo.todoapp.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UpdateTaskRequest {
    private String id;
    private String title;
    private String description;
    private String userId;
}
