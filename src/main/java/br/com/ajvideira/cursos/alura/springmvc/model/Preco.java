package br.com.ajvideira.cursos.alura.springmvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Preco implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4844371768215407540L;

	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private TipoPreco tipo;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Preco [valor=" + valor + ", tipo=" + tipo + "]";
	}	
	
}
