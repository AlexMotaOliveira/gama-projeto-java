package com.gama.model.dto;

import com.gama.model.enums.StatusAluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinasDTO {

    private String codigo;
    private String disciplina;
    private BigDecimal media= new BigDecimal(0);
    private StatusAluno status;
    private List<NotasDTO> notas = new ArrayList<>();


    public void status() {

        if (this.notas.size() >= 4) {
            calcularMedia();
            if (this.media.compareTo(new BigDecimal(6)) >= 0) {
                this.status = StatusAluno.APROVADO;

            } else if (media.compareTo(new BigDecimal(4)) >= 0) {
                this.status = StatusAluno.RECUPERACAO;
            } else {
                this.status = StatusAluno.REPROVADO;
            }
        } else {
            this.status = StatusAluno.NOTA_PENDENTE;
        }
    }

    private BigDecimal calcularMedia() {
        if (this.notas.isEmpty()) {
            return this.media;
        }
        for (NotasDTO nota : notas) {
            this.media =  this.media.add(nota.getValorNota());
        }
        this.media = this.media.divide(new BigDecimal(4)).setScale(2, RoundingMode.CEILING);
        return this.media;
    }
}
