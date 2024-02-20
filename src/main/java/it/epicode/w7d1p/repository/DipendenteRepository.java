package it.epicode.w7d1p.repository;

import it.epicode.w7d1p.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente,Integer>, PagingAndSortingRepository<Dipendente,Integer> {
}
