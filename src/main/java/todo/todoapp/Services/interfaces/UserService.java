package todo.todoapp.Services.interfaces;

import todo.todoapp.dtos.requests.LoginRequest;
import todo.todoapp.dtos.requests.SignUpRequest;
import todo.todoapp.dtos.responses.LoginResponse;
import todo.todoapp.dtos.responses.SignUpResponse;

public interface UserService {
    SignUpResponse signUp(SignUpRequest request);
    LoginResponse login(LoginRequest request);
    void addTask();
    void viewTask();
    void deleteTask();
    void viewAllTask();

}
