package todo.todoapp.exceptions;

public class BlankInputException extends TodoAppExceptions{
    public BlankInputException() {
        super("Input Cannot Be Blank");
    }
}
