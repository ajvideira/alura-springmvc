package br.com.ajvideira.cursos.alura.springmvc.model;

import java.math.BigDecimal;

public class DadosPagamento {

	private BigDecimal value;
	
	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
