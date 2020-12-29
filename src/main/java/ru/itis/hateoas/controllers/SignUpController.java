package ru.itis.hateoas.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hateoas.forms.SignUpForm;
import ru.itis.hateoas.services.SignUpService;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignUpForm signUpForm) {
        return ResponseEntity
                .ok()
                .body(signUpService.signUp(signUpForm));
    }
}
