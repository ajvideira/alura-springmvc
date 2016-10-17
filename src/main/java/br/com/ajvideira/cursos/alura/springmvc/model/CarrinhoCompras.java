package br.com.ajvideira.cursos.alura.springmvc.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens;
	
	public CarrinhoCompras() {
		itens = new LinkedHashMap<CarrinhoItem, Integer>();
	}
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidadeItem(item)+1);
	}

	private Integer getQuantidadeItem(CarrinhoItem item) {
		if (itens.containsKey(item)) {
			return itens.get(item);
		} else {
			return 0;
		}
	}
	
	public Integer getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulado) -> acumulado + proximo);
	}
	
}
