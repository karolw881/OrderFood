package com.example.demo.controles;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    public ResponseEntity<String> handleError(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
        HttpStatus status = HttpStatus.valueOf((Integer) attributes.get("status"));
        String message = (String) attributes.getOrDefault("message", "Nieznany błąd");

        if (status == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Błąd 404: Nie znaleziono zasobu.");
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd 500: Wewnętrzny błąd serwera.");
        } else if (status == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Błąd 401: Brak autoryzacji.");
        }

        return ResponseEntity.status(status).body("Błąd " + status.value() + ": " + message);
    }
}
