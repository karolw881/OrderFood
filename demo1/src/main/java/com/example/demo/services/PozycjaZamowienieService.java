package com.example.demo.services;

import com.example.demo.repo.PozycjaZamowienieRepository;
import com.example.demo.classes.PozycjaZamowienie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PozycjaZamowienieService {

    private final PozycjaZamowienieRepository pozycjaZamowienierepository;

    public PozycjaZamowienieService(PozycjaZamowienieRepository pozycjaZamowienierepository) {
        this.pozycjaZamowienierepository = pozycjaZamowienierepository;
    }

    public PozycjaZamowienie createPozycjaZamowienie(PozycjaZamowienie pz) {
        return pozycjaZamowienierepository.save(pz);
    }


    public void deletePozycjaZamowienie(PozycjaZamowienie pz) {
        pozycjaZamowienierepository.delete(pz);
    }

    public List<PozycjaZamowienie> getAllpozycjaZamowienie() {
        return pozycjaZamowienierepository.findAll();
    }

    public void updatePozycjaZamowienie(PozycjaZamowienie pz) {
        pozycjaZamowienierepository.save(pz);
    }



}
