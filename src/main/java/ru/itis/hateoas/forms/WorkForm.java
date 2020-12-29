package ru.itis.hateoas.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import ru.itis.hateoas.models.embedded.Item;
import ru.itis.hateoas.models.embedded.Tool;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkForm {

    private String description;
    private ObjectId client;
    private String carMark;
    private String carNumber;
    private List<Tool> tools;
    private List<Item> items;
    private Double workCost;
}
