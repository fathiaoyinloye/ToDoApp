package todo.todoapp.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Getter
@Setter
public class Task {
    @Id
    private String id;
    private String tittle;
    private Instant dateCreated = Instant.now();
    private Instant dateEdited;
    private String description;



}
