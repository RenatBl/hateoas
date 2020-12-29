package ru.itis.hateoas.services;

import org.bson.types.ObjectId;
import ru.itis.hateoas.dto.ClientDto;
import ru.itis.hateoas.forms.ClientForm;
import ru.itis.hateoas.models.Client;

import java.util.List;

public interface ClientsService {

    Client newClient(ClientForm form);

    ClientDto getClientById(ObjectId id);

    List<ClientDto> getAllClients();

    Client save(Client client);

    Client findClientById(ObjectId id);
}
