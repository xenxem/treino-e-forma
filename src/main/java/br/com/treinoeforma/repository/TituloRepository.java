package br.com.treinoeforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

}
