package ru.itis.hateoas.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExecutionForm {

    private ObjectId id;
    private String comment;
    private Boolean isFinished;
}
