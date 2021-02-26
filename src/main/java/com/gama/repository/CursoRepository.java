package com.gama.repository;

import com.gama.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query(value = "select * from Curso c inner join aluno_cursos ac on ac.aluno_id " +
            "= c.id and c.id=ac.cursos_id where c.id = ?1", nativeQuery = true
    )
    List<Curso> listaCursos (Long id);

}
