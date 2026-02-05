package todo.todoapp.Services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todo.todoapp.Services.interfaces.TaskService;
import todo.todoapp.data.repositories.TaskRepository;
import todo.todoapp.dtos.requests.AddTaskRequest;
import todo.todoapp.dtos.requests.DeleteTaskRequest;
import todo.todoapp.dtos.requests.UpdateTaskRequest;
import todo.todoapp.dtos.requests.ViewTaskRequest;
import todo.todoapp.dtos.responses.AddTaskResponse;
import todo.todoapp.dtos.responses.ViewTaskResponse;
import todo.todoapp.exceptions.BlankInputException;
import todo.todoapp.exceptions.InvalidTaskException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceImplTest {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskService taskService;
    @BeforeEach
    void SetUp(){
        taskRepository.deleteAll();
    }
    @Test
    void testThatTaskCanBeAdded() {
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
        taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
    }
        @Test
        void testThatTaskOneWasAdded_TaskIsOne_TaskTwoWasAddedTaskIsTwo(){
            assertEquals(0, taskRepository.count());
            AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
            taskService.addTask(addTask);
            assertEquals(1, taskRepository.count());
            AddTaskRequest addTask2 = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScrip").description("I want to be able do do...").build();
            taskService.addTask(addTask);
            assertEquals(2, taskRepository.count());
        }


    @Test
    void testThatTaskCanotbeAddedWhenItIsEmpty(){
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("").description("I want to be able do do...").build();
        assertThrows(BlankInputException.class, ()->taskService.addTask(addTask));
    }

    @Test
    void testThatTaskAWasAdded_TaskAWasDeletedById(){
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
       AddTaskResponse response = taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
        DeleteTaskRequest deleteTaskRequest =  new DeleteTaskRequest();
        deleteTaskRequest.setId(response.getId());
        taskService.deleteTask(deleteTaskRequest);
    }
    @Test
    void testThatTaskAWasAdded_TaskBWasAdded_TaskAWasDeletedById_TaskIsOne(){
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
        AddTaskResponse response = taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
        AddTaskRequest addTask2 = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScrip").description("I want to be able do do...").build();
        AddTaskResponse response2 = taskService.addTask(addTask);
        assertEquals(2, taskRepository.count());
        DeleteTaskRequest deleteTaskRequest =  new DeleteTaskRequest();
        deleteTaskRequest.setId(response.getId());
        taskService.deleteTask(deleteTaskRequest);
        assertEquals(1, taskRepository.count());


    }
    @Test
    void testThatTaskAWasAdded_TaskBWasAdded_TaskAWasDeletedByInvalidId_ThrowsException(){
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
        AddTaskResponse response = taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
        AddTaskRequest addTask2 = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScrip").description("I want to be able do do...").build();
        AddTaskResponse response2 = taskService.addTask(addTask);
        assertEquals(2, taskRepository.count());
        DeleteTaskRequest deleteTaskRequest =  new DeleteTaskRequest();
        deleteTaskRequest.setId("yuijk");
        assertThrows(InvalidTaskException.class, ()-> taskService.deleteTask(deleteTaskRequest));
        assertEquals(2, taskRepository.count());



    }


    @Test
    void testThatTaskAWasAdded_TaskAWasEdited_NameEdited_DescriptionEdited() {
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
        AddTaskResponse response = taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
        taskService.updateTask(UpdateTaskRequest.builder().id(response.getId()).title("Learn Crochet").description("I want to be able do do Make Crochet Shoe...").build());
        assertEquals(1, taskRepository.count());
        assertEquals("Learn Crochet", taskRepository.findAll().getFirst().getTittle());
        assertEquals("I want to be able do do Make Crochet Shoe...", taskRepository.findAll().getFirst().getDescription());
    }
    @Test
    void testThatTaskAWasAdded_TaskAWasEdited_NameEdited_DescriptionRemainTheSame() {
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
        AddTaskResponse response = taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
        taskService.updateTask(UpdateTaskRequest.builder().id(response.getId()).title("Learn Crochet").build());
        assertEquals(1, taskRepository.count());
        assertEquals("Learn Crochet", taskRepository.findAll().getFirst().getTittle());
        assertEquals("I want to be able do do...", taskRepository.findAll().getFirst().getDescription());
    }
    @Test
    void testThatTaskAWasAdded_TaskACanBeViewed() {
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
        AddTaskResponse response = taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
        ViewTaskResponse viewTaskResponse = taskService.viewTask(ViewTaskRequest.builder().id(response.getId()).build());
        assertEquals("Learn HTML, CSS and JavaScript", viewTaskResponse.getTitle());
        assertEquals("I want to be able do do...", viewTaskResponse.getDescription());
    }
    @Test
    void testThatTaskWasViewedWithANInvalidId_ThrowsException() {
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
        AddTaskResponse response = taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
        assertThrows(InvalidTaskException.class, ()-> taskService.viewTask(ViewTaskRequest.builder().id("543").build()));

    }
    @Test
    void testThatTaskAWasAdded_TaskBWasAdded_TaskAandBCanBeViewed(){
        assertEquals(0, taskRepository.count());
        AddTaskRequest addTask = AddTaskRequest.builder().title("Learn HTML, CSS and JavaScript").description("I want to be able do do...").build();
        AddTaskResponse response = taskService.addTask(addTask);
        assertEquals(1, taskRepository.count());
        AddTaskRequest addTask2 = AddTaskRequest.builder().title("Learn Crochet").description("I want to be able do do Crochet...").build();
        AddTaskResponse response2 = taskService.addTask(addTask2);
        assertEquals(2, taskRepository.count());
        List<ViewTaskResponse> viewTaskResponseList = taskService.viewAllTask();

        assertEquals("Learn HTML, CSS and JavaScript", viewTaskResponseList.get(0).getTitle() );
        assertEquals("Learn Crochet", viewTaskResponseList.get(1).getTitle() );
        assertEquals("I want to be able do do...", viewTaskResponseList.get(0).getDescription() );
        assertEquals("I want to be able do do Crochet...", viewTaskResponseList.get(1).getDescription() );

    }

}