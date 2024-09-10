package com.example.demo.repo;
import com.example.demo.classes.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {
    Optional<Zamowienie> findFirstByOrderByIdDesc();


}