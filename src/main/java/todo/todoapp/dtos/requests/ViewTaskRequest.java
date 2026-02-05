package todo.todoapp.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ViewTaskRequest {
    private String id;
    private String userId;
}
