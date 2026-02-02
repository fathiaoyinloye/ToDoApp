package todo.todoapp.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import todo.todoapp.utils.PasswordUtil;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SignUpRequest {
    @NotNull(message = "Username must be filled")
    @NotBlank(message = "Name must not be left blank")
    private String name;


    @NotNull(message = "Username must be filled")
    @NotBlank(message = "Username must not be left blank")
    @Size(min = 5, message = "UserName must be at least five characters")
    private String userName;


    @NotNull(message = "Password must be filled")
    @NotBlank(message = "Password must not be left blank")
    @Size(min = 5, message = "Password must be at least five characters")
    private String password;

    public void setPassword(String password){
        this.password = PasswordUtil.hashPassword(password);
    }
}
