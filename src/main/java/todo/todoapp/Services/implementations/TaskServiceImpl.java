package todo.todoapp.Services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.todoapp.Services.interfaces.TaskService;
import todo.todoapp.data.models.Task;
import todo.todoapp.data.repositories.TaskRepository;
import todo.todoapp.dtos.requests.AddTaskRequest;
import todo.todoapp.dtos.requests.DeleteTaskRequest;
import todo.todoapp.dtos.requests.UpdateTaskRequest;
import todo.todoapp.dtos.requests.ViewTaskRequest;
import todo.todoapp.dtos.responses.AddTaskResponse;
import todo.todoapp.dtos.responses.DeleteResponse;
import todo.todoapp.dtos.responses.UpdateTaskResponse;
import todo.todoapp.dtos.responses.ViewTaskResponse;
import todo.todoapp.exceptions.InvalidTaskException;
import todo.todoapp.utils.Mappers;
import todo.todoapp.utils.Validators;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Override
    public AddTaskResponse addTask(AddTaskRequest request) {
       validateInput(request);
       Task task = new Task();
       Mappers.MapAddTaskRequest(request,task);
       taskRepository.save(task);
       return Mappers.MapAddTaskResponse(task);
    }

    @Override
    public DeleteResponse deleteTask(DeleteTaskRequest request) {
        Task task = findTask(request.getId());
        taskRepository.delete(task);
        return Mappers.mapDeleteResponse();
    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskRequest request) {
        Validators.validateEmptyId(request.getId());
        Task task = findTask(request.getId());
        Mappers.mapUpdateTaskRequest(request, task);
        taskRepository.save(task);
        return Mappers.mapUpdateTaskResponse(task);
    }

    private Task findTask(String id){
        return taskRepository.findById(id).orElseThrow(InvalidTaskException::new);
    }


    public List<ViewTaskResponse> viewAllTask() {
        List<ViewTaskResponse> viewTaskResponses = new ArrayList<>();
        for(int index = 0; index < taskRepository.count(); index++){
            ViewTaskResponse viewTaskResponse = ViewTaskResponse.builder()
                    .title(taskRepository.findAll().get(index).getTittle())
                    .description(taskRepository.findAll().get(index).getDescription())
                    .id(taskRepository.findAll().get(index).getId())
                    .dateCreated(taskRepository.findAll().get(index).getDateCreated()).build();
            viewTaskResponses.add(viewTaskResponse);
        }

        return viewTaskResponses;
    }

    @Override
    public ViewTaskResponse viewTask(ViewTaskRequest request) {
        Task task = findTask(request.getId());
        return Mappers.mapViewTaskResponse(task);
    }

    private void validateInput(AddTaskRequest request){
        Validators.validateEmptyRequest(request.getTitle());
        Validators.validateEmptyRequest(request.getDescription());
    }


    private void validateThatTaskDoesNot(AddTaskRequest request){
        Validators.validateEmptyRequest(request.getTitle());
        Validators.validateEmptyRequest(request.getDescription());
    }
}
