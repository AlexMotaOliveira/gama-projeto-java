package com.gama.model;

import javax.persistence.*;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nome;
    private String cpf;
    private String email;
    private String numero;
    private String cidade;
    private String uf;
    private String cep;


    @OneToOne
    private Curso cursos = new Curso();
}
