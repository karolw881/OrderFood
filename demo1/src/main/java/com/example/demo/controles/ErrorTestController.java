package com.example.demo.controles;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/test-errors")
public class ErrorTestController {

    @GetMapping("/404")
    public ResponseEntity<String> testNotFound() {
        throw new EntityNotFoundException("Zasób nie został znaleziony");
    }

    @GetMapping("/401")
    public ResponseEntity<String> testUnauthorized() throws AccessDeniedException {
        throw new AccessDeniedException("Brak autoryzacji");
    }

    @GetMapping("/403")
    public ResponseEntity<String> testForbidden() throws AccessDeniedException {
        throw new AccessDeniedException("Brak uprawnień do zasobu");
    }

    @GetMapping("/400")
    public ResponseEntity<String> testBadRequest() {
        throw new IllegalArgumentException("Nieprawidłowe parametry żądania");
    }

    @GetMapping("/500")
    public ResponseEntity<String> testInternalError() {
        throw new RuntimeException("Wystąpił wewnętrzny błąd serwera");
    }
}