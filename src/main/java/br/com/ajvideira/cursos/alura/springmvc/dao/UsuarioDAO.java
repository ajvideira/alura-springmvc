package br.com.ajvideira.cursos.alura.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.cursos.alura.springmvc.model.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Usuario> usuarios = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class).setParameter("email", email).getResultList();
		
		if (usuarios != null && usuarios.size()>0) {
			return usuarios.get(0);
		} else {
			throw new UsernameNotFoundException("usuário não encontrado");
		}
	}
	
	public void gravar(Usuario usuario) {
		entityManager.persist(usuario);
	}
	
}
