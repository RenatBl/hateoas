package ru.itis.hateoas.mappers;

import org.mapstruct.Mapper;
import ru.itis.hateoas.dto.ClientDto;
import ru.itis.hateoas.models.Client;

import java.util.List;

@Mapper
public interface ClientsMapper {

    ClientDto entityToDto(Client client);

    List<ClientDto> entityToDto(List<Client> clients);

}
