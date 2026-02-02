package todo.todoapp.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
public class SignUpResponse {
    private String name;
    private String userName;
    private boolean active;
}
