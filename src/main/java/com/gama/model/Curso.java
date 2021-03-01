package com.gama.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 4, max = 10)
	@Column(nullable = false, length = 10)
	private String codigo;

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(nullable = false, length = 50)
	private String curso;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Disciplina> disciplinas = new ArrayList<>();
}
