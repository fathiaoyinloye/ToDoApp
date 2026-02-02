package todo.todoapp.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String name;
    private String userName;
    private boolean active;
}
