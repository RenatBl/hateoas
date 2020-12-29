package ru.itis.hateoas.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hateoas.dto.TokenDto;
import ru.itis.hateoas.forms.SignInForm;
import ru.itis.hateoas.services.SignInService;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class SignInController {

    private final SignInService signInService;

    @PostMapping
    public ResponseEntity<?> confirmLogin(@RequestBody SignInForm signInForm) {
        TokenDto token = signInService.signIn(signInForm);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            throw new IllegalArgumentException("Wrong data");
        }
    }
}
