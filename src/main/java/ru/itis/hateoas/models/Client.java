package ru.itis.hateoas.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "clients")
public class Client {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String firstName;
    private String lastName;

    private Double total;
    private Integer score;
    private Integer worksQuantity;
}
