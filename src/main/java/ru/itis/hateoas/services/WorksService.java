package ru.itis.hateoas.services;

import org.bson.types.ObjectId;
import ru.itis.hateoas.dto.WorkDto;
import ru.itis.hateoas.forms.WorkExecutionForm;
import ru.itis.hateoas.forms.WorkForm;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.models.Work;

import java.time.LocalDateTime;
import java.util.List;

public interface WorksService {

    Work newWork(WorkForm form, User user);

    WorkDto getWorkById(ObjectId id);

    List<WorkDto> getAllWorkByPeriod(LocalDateTime from, LocalDateTime to);

    List<WorkDto> getAllWorksByWorker(ObjectId workerId);

    List<WorkDto> getAllWorksByClient(ObjectId clientId);

    Work executeWork(WorkExecutionForm form);

}
