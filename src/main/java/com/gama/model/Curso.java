package com.gama.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Curso {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String curso;
	
	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@ManyToOne
	private Aluno aluno;
}
