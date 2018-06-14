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
			+ "WHERE te.treino.id=:codigo GROUP BY te.titulo")
	public List<Titulo> buscaTitulosPorTreino(@Param("codigo") Long codigo);
	
	@Query("SELECT te.exercicio "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.id =:codigoTreino "
			+ "AND te.titulo.id = :codigoTitulo ")
	public List<Exercicio> buscaExerciciosPorTitulo(@Param("codigoTreino") Long codigoTreino,@Param("codigoTitulo") Long codigoTitulo);
	
	@Query("SELECT te.titulo FROM TreinoExercicio te "
			+ "WHERE te.treino.usuario.id =:codigoUsuario "
			+ "GROUP BY te.titulo.id "
			+ " ORDER BY te.titulo.id DESC")
	public List<Titulo> listaTitulosDoTreino(@Param("codigoUsuario") Long codigoUsuario);
	
	@Query("SELECT te.exercicio "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.id=:codigo")
	public List<Titulo> buscaExerciciosPorTreino(@Param("codigo") Long codigo);
	
}
