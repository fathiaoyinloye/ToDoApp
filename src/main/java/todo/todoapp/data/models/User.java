package todo.todoapp.data.models;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class User {

    private String name;
    @Id
    private String username;
    private String password;
    private boolean active;
    private List<Task> tasks;


}
