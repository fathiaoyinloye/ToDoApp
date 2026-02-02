package todo.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.todoapp.Services.interfaces.TaskService;
import todo.todoapp.dtos.requests.AddTaskRequest;
import todo.todoapp.dtos.requests.DeleteTaskRequest;
import todo.todoapp.dtos.requests.UpdateTaskRequest;
import todo.todoapp.dtos.requests.ViewTaskRequest;
import todo.todoapp.exceptions.BlankInputException;
import todo.todoapp.exceptions.InvalidTaskException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequest request) {
        try {
            return ResponseEntity.status(CREATED).body(taskService.addTask(request));
        } catch (BlankInputException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<?> deleteTask(@RequestBody DeleteTaskRequest request) {
        try {
            return ResponseEntity.status(OK).body(taskService.deleteTask(request));
        } catch (BlankInputException | InvalidTaskException e) {
            return ResponseEntity.status(OK).body(e.getMessage());

        }
    }
    @PutMapping("/update-task")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest request) {
        try {
            return ResponseEntity.status(OK).body(taskService.updateTask(request));
        } catch (BlankInputException | InvalidTaskException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }

    @GetMapping("/view-task")
    public ResponseEntity<?> updateTask(@RequestBody ViewTaskRequest request) {
        try {
            return ResponseEntity.status(OK).body(taskService.viewTask(request));
        } catch (BlankInputException | InvalidTaskException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }

    @GetMapping("/view-all-task")
    public ResponseEntity<?> updateTask() {
          return   ResponseEntity.status(OK).body(taskService.viewAllTask());
    }
}
