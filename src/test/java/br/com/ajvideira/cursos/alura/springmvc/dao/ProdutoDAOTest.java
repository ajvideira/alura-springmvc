package br.com.ajvideira.cursos.alura.springmvc.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.ajvideira.cursos.alura.springmvc.configuration.DataSourceConfigurationTest;
import br.com.ajvideira.cursos.alura.springmvc.configuration.JPAConfiguration;
import br.com.ajvideira.cursos.alura.springmvc.model.Preco;
import br.com.ajvideira.cursos.alura.springmvc.model.Produto;
import br.com.ajvideira.cursos.alura.springmvc.model.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDAOTest {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Before
	public void insertProducts() {
		List<Produto> produtos;
		
		produtos = createProducts(TipoPreco.IMPRESSO, 5);
		for (Produto produto : produtos) {
			produtoDAO.salvar(produto);
		}
		
		produtos = createProducts(TipoPreco.EBOOK, 5);
		for (Produto produto : produtos) {
			produtoDAO.salvar(produto);
		}
	}
	
	@Test
	@Transactional
	public void sumAllProductsByType() {
		Assert.assertEquals(new BigDecimal(50).setScale(2), produtoDAO.sumAllProductsByType(TipoPreco.IMPRESSO).setScale(2));
	}
	
	public static Produto createProduct(TipoPreco tipo, Integer label) {
		
		List<Preco> precos = new ArrayList<>();
		precos.add(new Preco(tipo, BigDecimal.TEN));
		
		return new Produto.Builder("Livro " + label)
				.descricao("Descrição do livro " + label)
				.paginas(200+label)
				.imagemPath("")
				.precos(precos)
				.build();
	}
	
	public static List<Produto> createProducts(TipoPreco tipo, Integer num) {
		List<Produto> produtos = new ArrayList<>();
		
		for (int i=1; i <= num; i++) {
			produtos.add(createProduct(tipo, i));
		}
		
		return produtos;
	}
	
}
