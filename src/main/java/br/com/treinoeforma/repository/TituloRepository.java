package br.com.treinoeforma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {
	
	@Query("SELECT te.titulo "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.id=:tituloId "
			+ "GROUP BY te.titulo")
	public List<Titulo> buscaTitulosPorTreino(@Param("tituloId") Long tituloId);
	
	@Query("SELECT te.exercicio "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.id =:treinoId "
			+ "AND te.titulo.id = :tituloId")
	public List<Exercicio> buscaExerciciosPorTitulo(@Param("treinoId") Long treinoId,@Param("tituloId") Long tituloId);
	
	@Query("SELECT te.titulo FROM TreinoExercicio te "
			+ "WHERE te.treino.usuario.id =:usuarioId "
			+ "GROUP BY te.titulo.id "
			+ " ORDER BY te.titulo.id DESC")
	public List<Titulo> listaTitulosDoTreino(@Param("usuarioId") Long usuarioId);
	
	@Query("SELECT te.exercicio "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.id=:treinoId")
	public List<Titulo> buscaExerciciosPorTreino(@Param("treinoId") Long treinoId);
	
}
