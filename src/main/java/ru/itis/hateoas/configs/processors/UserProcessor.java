package ru.itis.hateoas.configs.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.models.User;

@Component
@RequiredArgsConstructor
public class UserProcessor implements RepresentationModelProcessor<EntityModel<User>> {

    private final RepositoryEntityLinks links;

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        return null;
    }
}
