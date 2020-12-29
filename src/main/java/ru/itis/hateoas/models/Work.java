package ru.itis.hateoas.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.itis.hateoas.models.embedded.Car;
import ru.itis.hateoas.models.embedded.Item;
import ru.itis.hateoas.models.embedded.Tool;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "works")
public class Work {

    @JsonSerialize(using = ToStringSerializer.class)
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

    private LocalDateTime dateOfWork;

    private Boolean isFinished;

    private String comment;
}
