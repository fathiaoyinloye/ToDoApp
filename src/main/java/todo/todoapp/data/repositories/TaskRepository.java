package todo.todoapp.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import todo.todoapp.data.models.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

}
