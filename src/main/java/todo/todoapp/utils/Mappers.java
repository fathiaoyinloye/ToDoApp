package todo.todoapp.utils;

import todo.todoapp.data.models.Task;
import todo.todoapp.data.models.User;
import todo.todoapp.dtos.requests.AddTaskRequest;
import todo.todoapp.dtos.requests.SignUpRequest;
import todo.todoapp.dtos.requests.UpdateTaskRequest;
import todo.todoapp.dtos.responses.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Mappers {
    public static void MapAddTaskRequest(AddTaskRequest request, Task task){
        task.setTittle(request.getTitle());
        task.setDescription(request.getDescription());
    }

    public static AddTaskResponse MapAddTaskResponse(Task task){
     return AddTaskResponse.builder()
            .id(task.getId())
            .title(task.getTittle())
            .body(task.getDescription())
            .dateCreated(task.getDateCreated())
            .build();
    }

    public static void mapUpdateTaskRequest(UpdateTaskRequest request, Task task){
        if (request.getDescription() != null && !request.getDescription().isEmpty()) task.setDescription(request.getDescription());
        if (request.getTitle() != null  && !request.getTitle().isEmpty()) task.setTittle(request.getTitle());
        task.setDateEdited(Instant.now());

    }

    public static UpdateTaskResponse mapUpdateTaskResponse(Task task){
        return UpdateTaskResponse.builder().id(task.getId()).title(task.getTittle()).description(task.getDescription()) .build();

    }
    public static ViewTaskResponse mapViewTaskResponse(Task task){
        return ViewTaskResponse.builder().id(task.getId()).title(task.getTittle()).description(task.getDescription()).dateCreated(task.getDateCreated()).build();

    }

    public static DeleteResponse mapDeleteResponse(){
        DeleteResponse response = new DeleteResponse();
        response.setMessage("Successfully Deleted");
        return  response;

    }


    public static User mapUserSignUpRequest(SignUpRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setActive(true);

        return user;
    }

    public static SignUpResponse mapUserSignUpResponse(User user){
        return SignUpResponse.builder().name(user.getName()).active(user.isActive())
                .username(user.getUsername()).build();
    }
    public static LoginResponse mapLoginResponse(User user){
        return LoginResponse.builder().name(user.getName()).active(user.isActive()).username(user.getUsername()).build();
    }

    public static List<ViewTaskResponse> MapViewAllTask(User user){
        List<ViewTaskResponse> viewTaskResponses = new ArrayList<>();
        for(int index = 0; index < user.getTasks().size(); index++){
            ViewTaskResponse viewTaskResponse = ViewTaskResponse.builder()
                    .title(user.getTasks().get(index).getTittle())
                    .description(user.getTasks().get(index).getDescription())
                    .id(user.getTasks().get(index).getId())
                    .dateCreated(user.getTasks().get(index).getDateCreated()).build();
            viewTaskResponses.add(viewTaskResponse);
        }

        return viewTaskResponses;

    }

}