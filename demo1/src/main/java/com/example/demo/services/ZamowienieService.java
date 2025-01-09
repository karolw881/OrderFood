package com.example.demo.services;

import com.example.demo.dtos.PozycjaZamowienieDTO;
import com.example.demo.classes.PozycjaZamowienie;
import com.example.demo.classes.Zamowienie;
import com.example.demo.dtos.ZamowienieDTO;
import com.example.demo.repo.ZamowienieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Component
public class ZamowienieService {

    @Autowired
    private final ZamowienieRepository zamowienieRepository;

    public ZamowienieService(ZamowienieRepository zamowienieRepository) {
        this.zamowienieRepository = zamowienieRepository;
    }


    public ZamowienieDTO createZamowienie(Zamowienie zamowienie) {
        Zamowienie z = zamowienieRepository.save(zamowienie);
        return ZamowienieDTO.builder()
                .id(z.getId())
                .typ(z.getTyp())
                .link(z.getLink())
                .do_kiedy(z.getDo_kiedy())
                .data(z.getData())
                .pozycje(z.getPozycje().stream()
                        .map(p -> PozycjaZamowienieDTO.builder()
                                .id(p.getId())
                                .opis(p.getOpis())
                                .cena(p.getCena())
                                .status(p.getStatus())
                                .sposob_platnosci(p.getSposob_platnosci())
                                .zamawiajacy(p.getZamawiajacy())
                                .build()
                        ).toList())
                .build();

    }



    public Zamowienie updateZamowienie(UUID id, Zamowienie pZamowienie) {
        Optional<Zamowienie> oZamowienie = zamowienieRepository.findById(id);
        if (oZamowienie.isPresent()) {
            Zamowienie dopodmiany = oZamowienie.get();
            dopodmiany.setDo_kiedy(pZamowienie.getDo_kiedy());
            dopodmiany.setId(pZamowienie.getId());
            System.out.println(pZamowienie.getId());
            dopodmiany.setLink(pZamowienie.getLink());
            dopodmiany.setTyp(pZamowienie.getTyp());
            return zamowienieRepository.save(dopodmiany);
        } else {
            throw new RuntimeException("Zamówienie o ID " + id + " nie zostało znalezione.");
        }
    }


    public void dodajPozycje(Zamowienie zamowienie, PozycjaZamowienieDTO pozycjaZamowienieDTO) {
        PozycjaZamowienie pozycjaZamowienieEntity = pozycjaZamowienieDTO.toEntity(); // Konwersja DTO do encji
        pozycjaZamowienieEntity.setZamowienie(zamowienie); // Ustawienie referencji do zamówienia
        zamowienie.getPozycje().add(pozycjaZamowienieEntity); // Dodanie pozycji do listy pozycji zamówienia
        // Nie ma potrzeby wywoływać metody save tutaj, ponieważ jest to już robione w kontrolerze
    }






/*
    public List<Zamowienie> getAllZamowienie() {
        return zamowienieRepository.findAll();
    }

 */

    public List<ZamowienieDTO> getAllZamowienie() {
        return zamowienieRepository.findAll().stream()
                .map(z -> ZamowienieDTO.builder()
                        .id(z.getId())
                        .typ(z.getTyp())
                        .link(z.getLink())
                        .data(z.getData())
                        .do_kiedy(z.getDo_kiedy())
                        .build())
                .collect(Collectors.toList());
    }



    public void delete(UUID i) {
        zamowienieRepository.deleteById(i);
    }

    public ZamowienieDTO getIdZamowienie(Long id) {

        return zamowienieRepository.findById(id).map(z -> ZamowienieDTO.builder()
                .id(z.getId())
                .typ(z.getTyp())
                .link(z.getLink())
                .do_kiedy(z.getDo_kiedy())
                .pozycje(z.getPozycje().stream()
                                .map(p -> PozycjaZamowienieDTO.builder()
                                                .id(p.getId())
                                                .opis(p.getOpis())
                                                .cena(p.getCena())
                                                .status(p.getStatus())
                                                .sposob_platnosci(p.getSposob_platnosci())
                                                .zamawiajacy(p.getZamawiajacy())

                                                .build()
                        ).toList())
                .build()).orElse(null);
    }

    public Optional<Zamowienie> findby(String id){
       return zamowienieRepository.findById(UUID.fromString(id));
    }





    public ZamowienieDTO getOstatnieZamowienie() {

        return zamowienieRepository.findFirstByOrderByDataDesc().map(z -> ZamowienieDTO.builder()
                .id(z.getId())
                .typ(z.getTyp())
                .link(z.getLink())
                .do_kiedy(z.getDo_kiedy())
                .pozycje(z.getPozycje().stream()
                        .map(p -> PozycjaZamowienieDTO.builder()
                                .id(p.getId())
                                .opis(p.getOpis())
                                .cena(p.getCena())
                                .status(p.getStatus())
                                .sposob_platnosci(p.getSposob_platnosci())
                                .zamawiajacy(p.getZamawiajacy())
                                .build()
                        ).toList())
                .build()).orElse(null);
    }

    public Zamowienie save(Zamowienie zamowienie) {
        Zamowienie za =  zamowienieRepository.save(zamowienie);
        zamowienieRepository.flush();
        return  za;
    }

    public void delateZamowienie(Zamowienie zamowienie) {
        zamowienieRepository.delete(zamowienie);
    }

    public List<Zamowienie> getAllUsers() {
        return zamowienieRepository.findAll();
    }



}
