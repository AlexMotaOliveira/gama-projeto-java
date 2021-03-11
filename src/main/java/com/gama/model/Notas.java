package com.gama.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DecimalMax(value = "10.00")
	@DecimalMin(value = "0.00")
	@Column(nullable = false)
	private BigDecimal valorNota;

	@NotBlank
	@Column(nullable = false)
	private String tipoNota;

	@ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.DETACH)
	private List<Disciplina> disciplinas = new ArrayList<>();
}
