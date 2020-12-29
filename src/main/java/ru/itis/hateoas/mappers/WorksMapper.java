package ru.itis.hateoas.mappers;

import org.mapstruct.Mapper;
import ru.itis.hateoas.dto.WorkDto;
import ru.itis.hateoas.models.Work;

import java.util.List;

@Mapper
public interface WorksMapper {

    WorkDto entityToDto(Work work);

    List<WorkDto> entityToDto(List<Work> works);
}
