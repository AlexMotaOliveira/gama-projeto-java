package com.gama.exception;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
    public static class Body{

        public Body(String nome, String mensagem) {
			this.setNome(nome);
			this.setMensagem(mensagem);
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		private String nome;
        private String mensagem;

    }
}



