package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Crud<T> {
	
	T salvar(T t);
	
	void excluir(Long id);
	
	List<T> listar();
	
	T buscar(Long id);
	
	Page<T> buscarPaginando(Pageable pageable);
	
	Page<T> listarPorUsuario(Long id,Pageable pageable);
	
}
