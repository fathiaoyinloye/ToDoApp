package todo.todoapp.Services.interfaces;

import todo.todoapp.dtos.requests.*;
import todo.todoapp.dtos.responses.*;

import java.util.List;

public interface UserService {
    SignUpResponse signUp(SignUpRequest request);
    LoginResponse login(LoginRequest request);
    AddTaskResponse addTask(AddTaskRequest request);
    ViewTaskResponse viewTask(ViewTaskRequest request);
    DeleteResponse deleteTask(DeleteTaskRequest deleteTaskRequest);
    UpdateTaskResponse updateTask(UpdateTaskRequest request);
    List<ViewTaskResponse> viewAllTask(ViewTaskRequest request);

}

