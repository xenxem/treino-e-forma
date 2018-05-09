package br.com.treinoeforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino,Long> {
	
}
