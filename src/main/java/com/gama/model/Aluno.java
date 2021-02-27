package com.gama.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluno{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nome;

    @CPF
    @NotBlank
    @Column(nullable = false, length = 11)
    private String cpf;

    @Email
    @NotBlank
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private Long matricula;

    @Valid
    @NonNull
    @Embedded
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
    private List<Curso> cursos = new ArrayList<>();

}
