package br.com.ajvideira.cursos.alura.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ajvideira.cursos.alura.springmvc.model.Produto;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Produto produto) {
		entityManager.persist(produto);
	}

	public List<Produto> listar() {
		List<Produto> produtos = new ArrayList<Produto>();
		TypedQuery<Produto> query = entityManager.createQuery("SELECT p from Produto p", Produto.class);
		produtos = query.getResultList();

		return produtos;
	}

	public Produto find(Integer id) {
		return entityManager.createQuery("SELECT p FROM Produto p JOIN FETCH p.precos WHERE p.id = :id", Produto.class)
				.setParameter("id", id).getSingleResult();
	}

}