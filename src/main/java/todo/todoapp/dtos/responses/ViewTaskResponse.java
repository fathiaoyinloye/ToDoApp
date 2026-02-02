package todo.todoapp.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class ViewTaskResponse {
    private String id;
    private String title;
    private Instant dateCreated;
    private String description;


}
