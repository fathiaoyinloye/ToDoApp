package todo.todoapp.Services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.todoapp.Services.interfaces.TaskService;
import todo.todoapp.Services.interfaces.UserService;
import todo.todoapp.data.models.Task;
import todo.todoapp.data.models.User;
import todo.todoapp.data.repositories.TaskRepository;
import todo.todoapp.data.repositories.UserRepository;
import todo.todoapp.dtos.requests.*;
import todo.todoapp.dtos.responses.*;
import todo.todoapp.exceptions.InvalidTaskException;
import todo.todoapp.exceptions.InvalidUsernameException;
import todo.todoapp.exceptions.UserDoesNotExistException;
import todo.todoapp.utils.Mappers;
import todo.todoapp.utils.Validators;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;




    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        validateNewUser(request.getUsername());
        User user = Mappers.mapUserSignUpRequest(request);
        userRepository.save(user);
        return Mappers.mapUserSignUpResponse(user);
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        validateUserName(request.getUsername());
        User user = findUser(request.getUsername());
        Validators.validatePassword(request.getPassword(), user);
        user.setActive(true);
        return Mappers.mapLoginResponse(user);
    }

    @Override
    public AddTaskResponse addTask(AddTaskRequest request) {
        User user = findUser(request.getUserId());
        if(user == null) throw new UserDoesNotExistException();
        AddTaskResponse response = taskService.addTask(request);
         user.addToTask(findTask(response.getId()));
        userRepository.save(user);
        return response;

    }

    @Override
    public ViewTaskResponse viewTask(ViewTaskRequest request) {
        User user = findUser(request.getUserId());
        if(user == null) throw new UserDoesNotExistException();
        return taskService.viewTask(request);
    }

    @Override
    public DeleteResponse deleteTask(DeleteTaskRequest deleteTaskRequest) {
        User user = findUser(deleteTaskRequest.getUserId());
        if(user == null) throw new UserDoesNotExistException();
        user.removeToTask(findTask(deleteTaskRequest.getId()));
        userRepository.save(user);
        return taskService.deleteTask(deleteTaskRequest);

    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskRequest request) {
        User user = findUser(request.getUserId());
        UpdateTaskResponse updateTaskResponse = taskService.updateTask(request);
        Task task = findTask(request.getId());
        user.removeToTask(task);
        user.addToTask(task);
        userRepository.save(user);
        return updateTaskResponse;
    }

    @Override
    public List<ViewTaskResponse> viewAllTask(ViewTaskRequest request) {
        User user = findUser(request.getUserId());
        return Mappers.MapViewAllTask(user);
    }


    private User findUser(String username){
        return userRepository.findByUsername(username);
    }
    private Task findTask(String id){
        return taskRepository.findById(id).orElseThrow(InvalidTaskException::new);
    }


    private void validateUserName(String username){
        if(findUser(username) == null) throw new UserDoesNotExistException();
    }
    private void validateNewUser(String username){
        if(findUser(username) != null) throw new InvalidUsernameException();
    }


}
