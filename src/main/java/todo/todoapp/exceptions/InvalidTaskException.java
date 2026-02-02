package todo.todoapp.exceptions;

public class InvalidTaskException extends TodoAppExceptions {
    public InvalidTaskException() {
        super("Task Not Found");
    }
}
