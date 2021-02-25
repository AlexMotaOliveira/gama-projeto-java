package com.gama.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String disciplina;
	private String conceito;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Notas> notas = new ArrayList<>();
}
