package ru.itis.hateoas.services.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.dto.WorkDto;
import ru.itis.hateoas.forms.WorkExecutionForm;
import ru.itis.hateoas.forms.WorkForm;
import ru.itis.hateoas.mappers.WorksMapper;
import ru.itis.hateoas.models.Client;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.models.Work;
import ru.itis.hateoas.models.embedded.Car;
import ru.itis.hateoas.models.embedded.Item;
import ru.itis.hateoas.repositories.WorksRepository;
import ru.itis.hateoas.services.ClientsService;
import ru.itis.hateoas.services.WorksService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorksServiceImpl implements WorksService {

    private final WorksRepository worksRepository;
    private final WorksMapper worksMapper;
    private final ClientsService clientsService;

    @Override
    public Work newWork(WorkForm form, User user) {
        return worksRepository.save(Work.builder()
                .worker(user.getId())
                .client(form.getClient())
                .car(Car.builder()
                        .mark(form.getCarMark())
                        .number(form.getCarNumber())
                        .build())
                .description(form.getDescription())
                .tools(form.getTools())
                .items(form.getItems())
                .cost(form.getWorkCost() + form.getItems().stream()
                        .map(Item::getPrice)
                        .reduce(Double::sum)
                        .orElse(0.0))
                .build());
    }

    @Override
    public WorkDto getWorkById(ObjectId id) {
        return worksMapper.entityToDto(findWorkById(id));
    }

    @Override
    public List<WorkDto> getAllWorkByPeriod(LocalDateTime from, LocalDateTime to) {
        return worksMapper.entityToDto(worksRepository.getAllByDateOfWorkBetween(from, to));
    }

    @Override
    public List<WorkDto> getAllWorksByWorker(ObjectId workerId) {
        return worksMapper.entityToDto(worksRepository.getAllByWorker(workerId));
    }

    @Override
    public List<WorkDto> getAllWorksByClient(ObjectId clientId) {
        return worksMapper.entityToDto(worksRepository.getAllByClient(clientId));
    }

    @Override
    public Work executeWork(WorkExecutionForm form) {
        Work work = findWorkById(form.getId());

        work.setIsFinished(form.getIsFinished());
        work.setComment(form.getComment());
        work.setDateOfWork(LocalDateTime.now());

        if (form.getIsFinished()) updateClientInfo(form);

        return work;
    }

    private Work findWorkById(ObjectId id) {
        return worksRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Work not found")
                );
    }

    private void updateClientInfo(WorkExecutionForm form) {
        Work work = findWorkById(form.getId());
        Client client = clientsService.findClientById(work.getClient());

        client.setTotal(client.getTotal() + work.getCost());
        int score = (int) (work.getCost() / 100.0);
        client.setScore(client.getScore() + score);
        client.setWorksQuantity(client.getWorksQuantity() + 1);

        clientsService.save(client);
    }
}