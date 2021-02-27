package com.gama.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@Embeddable
public class Endereco {

    @NotBlank
    @Size(min = 8, max = 8)
    @Column(name = "endereco_cep", nullable = false, length = 8)
    private String cep;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "endereco_logradouro", nullable = false, length = 100)
    private String logradouro;

    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "endereco_numero", nullable = false, length = 20)
    private String numero;

    @Size(max = 100)
    @Column(name = "endereco_complemento", length = 100)
    private String complemento;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "endereco_bairro", nullable = false, length = 50)
    private String bairro;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "endereco_cidade", nullable = false, length = 50)
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(name = "endereco_uf", nullable = false, length = 2)
    private String uf;

}
