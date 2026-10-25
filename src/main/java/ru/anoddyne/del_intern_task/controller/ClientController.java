package ru.anoddyne.del_intern_task.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.anoddyne.del_intern_task.dto.ClientRequestDto;
import ru.anoddyne.del_intern_task.dto.ClientResponseDto;
import ru.anoddyne.del_intern_task.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody @Valid ClientRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> showAll() {
        return ResponseEntity.ok(clientService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> view(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.view(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable Long id, @RequestBody @Valid ClientRequestDto updateDto) {
        return ResponseEntity.ok(clientService.update(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
