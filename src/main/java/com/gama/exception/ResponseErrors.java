package com.gama.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrors {

	private Integer status;
	private OffsetDateTime data;
	public String message;
	public List<Body> bodys;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
    public static class Body{

		private String nome;
        private String mensagem;

    }
}



