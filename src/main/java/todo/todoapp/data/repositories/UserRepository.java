package todo.todoapp.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import todo.todoapp.data.models.User;

@Repository
public interface UserRepository extends MongoRepository <User, String>{
    User findByUsername(String username);
}
