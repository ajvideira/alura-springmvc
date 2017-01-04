package br.com.ajvideira.cursos.alura.springmvc.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ajvideira.cursos.alura.springmvc.model.Produto;
import br.com.ajvideira.cursos.alura.springmvc.model.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@CacheEvict(value="allProducts", allEntries=true)
	public void salvar(Produto produto) {
		entityManager.persist(produto);
	}

	@Cacheable(value="allProducts")
	public List<Produto> listar() {
		List<Produto> produtos = new ArrayList<Produto>();
		TypedQuery<Produto> query = entityManager.createQuery("SELECT p FROM Produto p", Produto.class);
		produtos = query.getResultList();

		return produtos;
	}

	public Produto find(Integer id) {
		return entityManager.createQuery("SELECT p FROM Produto p JOIN FETCH p.precos WHERE p.id = :id", Produto.class)
				.setParameter("id", id).getSingleResult();
	}
	
	public BigDecimal sumAllProductsByType(TipoPreco tipo) {
		return entityManager.createQuery("SELECT SUM(pr.valor) FROM Produto p JOIN p.precos pr WHERE pr.tipo = :tipo", BigDecimal.class)
				.setParameter("tipo", tipo).getSingleResult();
	}

}
