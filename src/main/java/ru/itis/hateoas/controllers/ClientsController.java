package ru.itis.hateoas.controllers;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.dto.ClientDto;
import ru.itis.hateoas.forms.ClientForm;
import ru.itis.hateoas.models.Client;
import ru.itis.hateoas.services.ClientsService;

import java.util.List;

@RepositoryRestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final ClientsService clientsService;

    @PostMapping
    public ResponseEntity<Client> newClient(@RequestBody ClientForm form) {
        return ResponseEntity
                .ok(clientsService.newClient(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("id") ObjectId id) {
        return ResponseEntity
                .ok(clientsService.getClientById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity
                .ok(clientsService.getAllClients());
    }
}
