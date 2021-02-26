package com.gama.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Data
@Embeddable
public class Endereco {

    @NotBlank
    @Column(name = "endereco_cep", nullable = false, length = 9)
    private String cep;

    @NotBlank
    @Column(name = "endereco_logradouro", nullable = false, length = 50)
    private String logradouro;

    @NotBlank
    @Column(name = "endereco_numero", nullable = false, length = 20)
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @NotBlank
    @Column(name = "endereco_bairro", nullable = false, length = 50)
    private String bairro;

    @NotBlank
    @Column(name = "endereco_cidade", nullable = false, length = 50)
    private String cidade;

    @NotBlank
    @Column(name = "endereco_uf", nullable = false, length = 2)
    private String uf;

}
