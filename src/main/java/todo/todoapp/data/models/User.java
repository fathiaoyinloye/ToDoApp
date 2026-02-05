package todo.todoapp.data.models;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import todo.todoapp.utils.PasswordUtil;

import java.util.ArrayList;
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
    private List<Task> tasks = new ArrayList<>();
    public void setPassword(String password){
        this.password = PasswordUtil.hashPassword(password);
    }
    public void addToTask(Task task){tasks.add(task);}
   // public void removeToTask(Task task){tasks.remove(task);}
    public void removeToTask(Task taskToRemove) {
        if (this.tasks != null && taskToRemove != null)
            this.tasks.removeIf((task) -> task.getId().equals(taskToRemove.getId()));

    }

}
