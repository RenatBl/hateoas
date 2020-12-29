package ru.itis.hateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.hateoas.models.Client;

public interface ClientsRepository extends MongoRepository<Client, ObjectId> {
}
