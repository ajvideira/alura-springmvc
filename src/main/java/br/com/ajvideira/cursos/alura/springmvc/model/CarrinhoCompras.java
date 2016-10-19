package br.com.ajvideira.cursos.alura.springmvc.model;

import java.math.BigDecimal;
import java.util.Collection;
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
	
 	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}
 	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public Integer getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			return 0;
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulado) -> acumulado + proximo);
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getPreco().multiply(new BigDecimal(getQuantidade(item)));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}
	
}
