package com.gama.repository;

import com.gama.model.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {

    //Optional<Notas> (Long id);
}
