package ru.itis.hateoas.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import ru.itis.hateoas.models.embedded.Car;
import ru.itis.hateoas.models.embedded.Item;
import ru.itis.hateoas.models.embedded.Tool;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDto {

    private ObjectId id;
    private String description;

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId worker;

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId client;

    private Car car;
    private List<Tool> tools;
    private List<Item> items;
    private Double cost;
    private String dateOfWork;
    private Boolean isFinished;
    private String comment;
}
