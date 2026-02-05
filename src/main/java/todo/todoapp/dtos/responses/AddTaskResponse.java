package todo.todoapp.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Builder
@Getter
@Setter
public class AddTaskResponse {
    private String id;
    private String title;
    private Instant dateCreated;
    private String body;
    private String username;

}
