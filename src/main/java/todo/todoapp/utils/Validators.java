package todo.todoapp.utils;

import todo.todoapp.data.models.User;
import todo.todoapp.exceptions.BlankInputException;
import todo.todoapp.exceptions.InvalidPasswordException;
import todo.todoapp.exceptions.InvalidTaskException;

public class Validators {
    public static void validateEmptyRequest(String letters){
        if(letters == null || letters.isBlank()) throw  new BlankInputException();

    }
    public static void validateEmptyId(String id){
        if(id == null) throw  new InvalidTaskException();
        if(id.isEmpty()) throw new InvalidTaskException();

    }
    public static void validateId(String id){

    }

    public static void validatePassword(String password, User user){
        if(!PasswordUtil.verifyPassword(password, user.getPassword() )) throw new InvalidPasswordException();



    }

}
