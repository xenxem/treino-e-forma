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
	
	@Query("select te.titulo from TreinoExercicio te where te.treino.id=:codigo group by te.titulo")
	public List<Titulo> buscaTitulosPorTreino(@Param("codigo") Long codigo);
	
	@Query("select te.exercicio from TreinoExercicio te where te.treino.id =:codigoTreino and te.titulo.id = :codigoTitulo ")
	public List<Exercicio> buscaExerciciosPorTitulo(@Param("codigoTreino") Long codigoTreino,@Param("codigoTitulo") Long codigoTitulo);
	
	@Query("select te.titulo from TreinoExercicio te where te.treino.usuario.id =:codigoUsuario group by te.titulo.id order by te.titulo.id desc")
	public List<Titulo> listaTitulosDoTreino(@Param("codigoUsuario") Long codigoUsuario);
	
	@Query("select te.exercicio from TreinoExercicio te where te.treino.id=:codigo")
	public List<Titulo> buscaExerciciosPorTreino(@Param("codigo") Long codigo);
	
}
