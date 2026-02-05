package todo.todoapp.dtos.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class DeleteTaskRequest {
    private String id;
    private String userId;
}
