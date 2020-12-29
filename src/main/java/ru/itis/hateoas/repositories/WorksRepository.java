package ru.itis.hateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.hateoas.models.Work;

import java.time.LocalDateTime;
import java.util.List;

public interface WorksRepository extends MongoRepository<Work, ObjectId> {

    List<Work> getAllByDateOfWorkBetween(LocalDateTime from, LocalDateTime to);

    List<Work> getAllByWorker(ObjectId worker);

    List<Work> getAllByClient(ObjectId client);

}
