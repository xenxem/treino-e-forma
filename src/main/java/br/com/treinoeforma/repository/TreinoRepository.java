package br.com.treinoeforma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.Treino;
import br.com.treinoeforma.model.Usuario;

@Repository
public interface TreinoRepository extends JpaRepository<Treino,Long> {
	
	@Query("SELECT MAX(t.id) "
			+ "FROM Treino t "
			+ "WHERE t.usuario.id =:usuarioId")
	public Long buscaUltimo(@Param("usuarioId") Long usuarioId);
	
	
	@Query("SELECT t "
			+ "FROM Treino t "
			+ "WHERE t.usuario =:usuario "
			+ "ORDER BY t.data DESC")
	public List<Treino> listarPorUsuario(@Param("usuario") Usuario usuario);
	
	
	public List<Treino> findById(Long usuarioId);
	
}
