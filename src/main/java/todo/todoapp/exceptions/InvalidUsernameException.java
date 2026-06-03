package todo.todoapp.exceptions;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("Invalid username");
    }
}
