package com.example.demo.controles;


import com.example.demo.dtos.PozycjaZamowienieDTO;
import com.example.demo.dtos.ZamowienieDTO;
import com.example.demo.classes.Zamowienie;
import com.example.demo.services.ZamowienieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;





@RestController
@RequestMapping("/zamowienia")

public class RestWay{

    private final ZamowienieService zamowienieService;

    public RestWay(ZamowienieService zamowienieService) {
        this.zamowienieService = zamowienieService;
    }

    // GET /zamowienia - Get all orders
    @GetMapping
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public ResponseEntity<List<ZamowienieDTO>> getAllZamowieniaWithPozycje() {
        List<ZamowienieDTO> zamowienia = zamowienieService.getAllZamowienie();
        return ResponseEntity.ok(zamowienia);
    }
    // GET /zamowienia/{id} - Get single order
    @GetMapping("/{id}")
    public ResponseEntity<ZamowienieDTO> getZamowienie(@PathVariable String id) {
        Zamowienie zamowienie = zamowienieService.findby(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zamowienie not found with id: " + id));
        return ResponseEntity.ok(ZamowienieDTO.toDto(zamowienie));
    }

    // POST /zamowienia - Create new order
    @PostMapping
    public ResponseEntity<ZamowienieDTO> createZamowienie(@RequestBody Zamowienie zamowienie) {
        ZamowienieDTO createdZamowienie = zamowienieService.createZamowienie(zamowienie);
        return ResponseEntity.ok(createdZamowienie);
    }

    // PUT /zamowienia/{id} - Update order
    @PutMapping("/{id}")
    public ResponseEntity<Zamowienie> updateZamowienie(@PathVariable UUID id, @RequestBody Zamowienie zamowienie) {
        Zamowienie updatedZamowienie = zamowienieService.updateZamowienie(id, zamowienie);
        return ResponseEntity.ok(updatedZamowienie);
    }

    // DELETE /zamowienia/{id} - Delete order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZamowienie(@PathVariable UUID id) {
        zamowienieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // POST /zamowienia/{id}/pozycje - Add position to order
    @PostMapping("/{id}/pozycje")
    public ResponseEntity<?> addPozycjaToZamowienie(
            @PathVariable String id,
            @Valid @RequestBody PozycjaZamowienieDTO pozycjaZamowienieDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.append(error.getField())
                            .append(": ")
                            .append(error.getDefaultMessage())
                            .append("; ")
            );
            return ResponseEntity.badRequest().body(errors.toString());
        }

        Zamowienie zamowienie = zamowienieService.findby(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zamowienie not found with id: " + id));

        zamowienieService.dodajPozycje(zamowienie, pozycjaZamowienieDTO);
        zamowienieService.save(zamowienie);

        return ResponseEntity.ok().build();
    }

    // GET /zamowienia/latest - Get latest order
    @GetMapping("/latest")
    public ResponseEntity<ZamowienieDTO> getLatestZamowienie() {
        ZamowienieDTO zamowienieDTO = zamowienieService.getOstatnieZamowienie();
        return ResponseEntity.ok(zamowienieDTO);
    }


    // PUT /zamowienia/uuid/pozycje/id - get choosen position from choosen order

    @PutMapping("/{id1}/pozycje/{id2}")
    public ResponseEntity<?> updateChoosenPositionFromChoosenOrder(
            @PathVariable UUID id1,
            @PathVariable Integer id2,
            @Valid @RequestBody PozycjaZamowienieDTO pozycjaZamowienieDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        zamowienieService.updatePositionInOrder(id1, id2, pozycjaZamowienieDTO); // Teraz nie musimy try-catch
        return ResponseEntity.noContent().build(); // Zwracamy 204 No Content po udanej aktualizacji
    }


    // Exception handler class
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }


}