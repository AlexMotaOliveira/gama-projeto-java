package com.gama.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDisciplinaNotasDTO {

    private String nome;
    private Long matricula;
    private String curso;
    private List<DisciplinasDTO> disciplinas = new ArrayList<>();
    
}
