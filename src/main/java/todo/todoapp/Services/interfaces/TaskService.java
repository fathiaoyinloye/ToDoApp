package todo.todoapp.Services.interfaces;

import todo.todoapp.dtos.requests.AddTaskRequest;
import todo.todoapp.dtos.requests.DeleteTaskRequest;
import todo.todoapp.dtos.requests.UpdateTaskRequest;
import todo.todoapp.dtos.requests.ViewTaskRequest;
import todo.todoapp.dtos.responses.AddTaskResponse;
import todo.todoapp.dtos.responses.DeleteResponse;
import todo.todoapp.dtos.responses.UpdateTaskResponse;
import todo.todoapp.dtos.responses.ViewTaskResponse;

import java.util.List;

public interface TaskService {
    AddTaskResponse addTask (AddTaskRequest request);
    DeleteResponse deleteTask(DeleteTaskRequest request);
    UpdateTaskResponse updateTask(UpdateTaskRequest request);
    List<ViewTaskResponse> viewAllTask();
    ViewTaskResponse  viewTask(ViewTaskRequest request);


    }
