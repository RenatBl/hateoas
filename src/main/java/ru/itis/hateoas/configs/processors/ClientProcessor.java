package ru.itis.hateoas.configs.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.ClientsController;
import ru.itis.hateoas.forms.ClientForm;
import ru.itis.hateoas.models.Client;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class ClientProcessor implements RepresentationModelProcessor<EntityModel<Client>> {

    private final RepositoryEntityLinks links;

    @Override
    public EntityModel<Client> process(EntityModel<Client> model) {
        Client client = model.getContent();

        if (client != null) {
            model.add(linkTo(
                    methodOn(ClientsController.class).getClientById(client.getId())
            ).withRel("getClientById"));

            model.add(linkTo(
                    methodOn(ClientsController.class).getAllClients()
            ).withRel("getAllClients"));

            model.add(links.linkToItemResource(Client.class, client.getId()).withRel("delete"));
        } else {
            model.add(linkTo(
                    methodOn(ClientsController.class).newClient(new ClientForm("Anton", "Pirogov"))
            ).withRel("newClient"));
        }

        return model;
    }
}