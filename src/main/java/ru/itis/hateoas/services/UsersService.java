package ru.itis.hateoas.services;

import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itis.hateoas.dto.UserDto;
import ru.itis.hateoas.forms.UserForm;
import ru.itis.hateoas.models.User;

public interface UsersService extends UserDetailsService {

    UserDto getUserById(ObjectId id);

    User save(User user);

    User updateUserAccount(User user, UserForm form);

}
