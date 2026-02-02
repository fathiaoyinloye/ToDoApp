package todo.todoapp.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddTaskRequest {
    private String title;
    private String description;

}
