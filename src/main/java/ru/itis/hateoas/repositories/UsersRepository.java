package ru.itis.hateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.hateoas.models.User;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByUsername(String username);

}
