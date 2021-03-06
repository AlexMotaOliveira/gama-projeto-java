package com.gama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String nome;

    @CPF
    @NotBlank
    @Column(nullable = false, length = 11,unique = true)
    private String cpf;

    @Email
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100,unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private Long matricula;

    @Valid
    @NonNull
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
    private List<Curso> cursos = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Notas> notas = new ArrayList<>();

}
