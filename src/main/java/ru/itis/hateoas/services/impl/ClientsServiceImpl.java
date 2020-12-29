package ru.itis.hateoas.services.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.dto.ClientDto;
import ru.itis.hateoas.forms.ClientForm;
import ru.itis.hateoas.mappers.ClientsMapper;
import ru.itis.hateoas.models.Client;
import ru.itis.hateoas.repositories.ClientsRepository;
import ru.itis.hateoas.services.ClientsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;
    private final ClientsMapper clientsMapper;

    @Override
    public Client newClient(ClientForm form) {
        return save(Client.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .score(0)
                .total(0.0)
                .worksQuantity(0)
                .build());
    }

    @Override
    public ClientDto getClientById(ObjectId id) {
        return clientsMapper.entityToDto(findClientById(id));
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientsMapper.entityToDto(clientsRepository.findAll());
    }

    @Override
    public Client save(Client client) {
        return clientsRepository.save(client);
    }

    @Override
    public Client findClientById(ObjectId id) {
        return clientsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Client not found")
                );
    }
}
