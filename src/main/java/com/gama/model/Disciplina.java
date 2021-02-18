package com.gama.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Disciplina {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String disciplina;
	private String conceito;
	
	@ManyToOne
	private Curso curso;
	
	@OneToMany(mappedBy = "disciplina")
	private List<Notas> notas = new ArrayList<Notas>();

	

}
