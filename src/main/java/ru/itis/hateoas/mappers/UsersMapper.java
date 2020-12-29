package ru.itis.hateoas.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.itis.hateoas.dto.UserDto;
import ru.itis.hateoas.models.User;

@Mapper
public interface UsersMapper {

    @Mappings(
            @Mapping(target = "name", source = "user.username")
    )
    UserDto entityToDto(User user);

}
