package todo.todoapp.exceptions;

public class InvalidPasswordException extends TodoAppExceptions {
    public InvalidPasswordException() {
        super("Invalid Password, Try Again");
    }
}
