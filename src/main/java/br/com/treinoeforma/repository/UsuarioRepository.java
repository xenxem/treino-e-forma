package br.com.treinoeforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.treinoeforma.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
	Usuario findByLogin(String login);
	
	@Modifying(clearAutomatically = true)
	@Query("Update Usuario u Set u.senha=:senha "
			+ "Where u.id=:id")	
	String alterarSenha(@Param("senha") String senha,@Param("id") Long id);
	
}
