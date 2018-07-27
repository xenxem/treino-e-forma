package br.com.treinoeforma.dto;

import br.com.treinoeforma.model.Titulo;

public class TituloDTO {

	private Titulo titulo;
	private Long totalExercicios;
	
	
	public TituloDTO(Titulo titulo, Long totalExercicios) {
		super();
		this.titulo = titulo;
		this.totalExercicios = totalExercicios;
	}
	
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
	public Long getTotalExercicios() {
		return totalExercicios;
	}
	public void setTotalExercicios(Long totalExercicios) {
		this.totalExercicios = totalExercicios;
	}
	
	
	
	
	
	
	
}
