package com.gama.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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


	@OneToMany(mappedBy = "aluno")
	private List<Curso> cursos = new ArrayList<Curso>();
}
