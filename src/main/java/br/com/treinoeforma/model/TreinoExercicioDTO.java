package br.com.treinoeforma.model;

public class TreinoExercicioDTO {
	
	private Treino treino;
	private Exercicio exercicio;
	private Titulo titulo;
	private Long ultimo;
	
	public TreinoExercicioDTO(Treino treino, Exercicio exercicio,Titulo titulo, Long ultimo) {
		this.treino = treino;
		this.exercicio = exercicio;
		this.titulo = titulo;
		this.ultimo = ultimo;
	}
	
	
	
	public Titulo getTitulo() {
		return titulo;
	}



	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}



	public Treino getTreino() {
		return treino;
	}
	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	public Exercicio getExercicio() {
		return exercicio;
	}
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
	public Long getUltimo() {
		return ultimo;
	}
	public void setUltimo(Long ultimo) {
		this.ultimo = ultimo;
	}
	
	
	

}
