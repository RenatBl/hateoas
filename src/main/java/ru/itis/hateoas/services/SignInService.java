package ru.itis.hateoas.services;

import ru.itis.hateoas.dto.TokenDto;
import ru.itis.hateoas.forms.SignInForm;

public interface SignInService {

    TokenDto signIn(SignInForm form);

}
