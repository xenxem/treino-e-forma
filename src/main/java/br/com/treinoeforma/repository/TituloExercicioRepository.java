package br.com.treinoeforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.TituloExercicio;

@Repository
public interface TituloExercicioRepository extends JpaRepository<TituloExercicio, Long> {

}
