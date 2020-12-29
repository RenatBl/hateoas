package ru.itis.hateoas.services;

import ru.itis.hateoas.forms.SignUpForm;
import ru.itis.hateoas.models.User;

public interface SignUpService {

    User signUp(SignUpForm form);

}
