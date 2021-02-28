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
public class DisciplinasDTO {

    private String codigo;
    private String disciplina;
    private List<NotasDTO> notas = new ArrayList<>();

}
