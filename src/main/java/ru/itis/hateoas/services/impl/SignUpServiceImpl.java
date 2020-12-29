package ru.itis.hateoas.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.forms.SignUpForm;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.models.enums.Role;
import ru.itis.hateoas.repositories.UsersRepository;
import ru.itis.hateoas.services.SignUpService;
import ru.itis.hateoas.services.UsersService;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(SignUpForm form) {
        return usersService.save(User.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .role(Role.WORKER)
                .build());
    }
}
