package ru.itis.hateoas.configs.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.models.Work;

@Component
@RequiredArgsConstructor
public class WorkProcessor implements RepresentationModelProcessor<EntityModel<Work>> {

    private final RepositoryEntityLinks links;

    @Override
    public EntityModel<Work> process(EntityModel<Work> model) {
        return null;
    }
}
