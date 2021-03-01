package com.gama.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(nullable = false, length = 50)
	private String codigo;

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(nullable = false, length = 50)
	private String disciplina;

	@NotBlank
	@Size(min = 20, max = 255)
	@Column(nullable = false, length = 255)
	private String conceito;



}
