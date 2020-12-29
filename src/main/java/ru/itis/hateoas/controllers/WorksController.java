package ru.itis.hateoas.controllers;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.dto.WorkDto;
import ru.itis.hateoas.forms.WorkExecutionForm;
import ru.itis.hateoas.forms.WorkForm;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.models.Work;
import ru.itis.hateoas.services.WorksService;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/works")
@RequiredArgsConstructor
public class WorksController {

    private final WorksService worksService;

    @PostMapping
    public ResponseEntity<Work> newWork(@RequestBody WorkForm form,
                                        @AuthenticationPrincipal User user) {
        return ResponseEntity
                .ok(worksService.newWork(form, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDto> getWorkById(@PathVariable("id") ObjectId id) {
        return ResponseEntity
                .ok(worksService.getWorkById(id));
    }

    @GetMapping
    public ResponseEntity<List<WorkDto>> getAllWorkByPeriod(@RequestParam("from") String from,
                                                            @RequestParam("to") String to) {
        return ResponseEntity
                .ok(worksService.getAllWorkByPeriod(LocalDateTime.parse(from), LocalDateTime.parse(to)));
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<List<WorkDto>> getAllWorksByWorker(@PathVariable("id") ObjectId workerId) {
        return ResponseEntity
                .ok(worksService.getAllWorksByWorker(workerId));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<WorkDto>> getAllWorksByClient(@PathVariable("id") ObjectId clientId) {
        return ResponseEntity
                .ok(worksService.getAllWorksByClient(clientId));
    }

    @PatchMapping
    public ResponseEntity<Work> executeWork(@RequestBody WorkExecutionForm form) {
        return ResponseEntity
                .ok(worksService.executeWork(form));
    }
}
