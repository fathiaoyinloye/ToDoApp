package todo.todoapp.Services.implementations;

import org.springframework.stereotype.Service;
import todo.todoapp.Services.interfaces.TaskService;
import todo.todoapp.Services.interfaces.UserService;
import todo.todoapp.data.models.User;
import todo.todoapp.data.repositories.UserRepository;
import todo.todoapp.dtos.requests.LoginRequest;
import todo.todoapp.dtos.requests.SignUpRequest;
import todo.todoapp.dtos.responses.LoginResponse;
import todo.todoapp.dtos.responses.SignUpResponse;
import todo.todoapp.exceptions.InvalidPasswordException;
import todo.todoapp.exceptions.UserDoesNotExistException;
import todo.todoapp.utils.Mappers;
import todo.todoapp.utils.PasswordUtil;
import todo.todoapp.utils.Validators;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TaskService taskService;

    public UserServiceImpl(UserRepository userRepository, TaskService taskService){
        this.userRepository = userRepository;
        this.taskService = taskService;
    }


    @Override
    public SignUpResponse signUp(SignUpRequest request) {
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
    public void addTask() {

    }

    @Override
    public void viewTask() {

    }

    @Override
    public void deleteTask() {

    }

    @Override
    public void viewAllTask() {

    }

    private User findUser(String username){
        return userRepository.findByUsername(username);
    }

    private void validateUserName(String username){
        if(findUser(username) == null) throw new UserDoesNotExistException();
    }


}
