package ru.itis.hateoas.controllers;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.dto.UserDto;
import ru.itis.hateoas.forms.UserForm;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.services.UsersService;

@RepositoryRestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PutMapping
    public ResponseEntity<User> updateUser(@AuthenticationPrincipal User user,
                                           @RequestBody UserForm userForm) {
        return ResponseEntity
                .ok(usersService.updateUserAccount(user, userForm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") ObjectId id) {
        return ResponseEntity
                .ok(usersService.getUserById(id));
    }
}
