package todo.todoapp.exceptions;

public class UserDoesNotExistException extends TodoAppExceptions {
    public UserDoesNotExistException() {
        super("Username does not exist");
    }
}
