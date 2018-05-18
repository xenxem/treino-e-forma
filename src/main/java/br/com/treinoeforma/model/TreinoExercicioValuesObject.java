package br.com.treinoeforma.model;

public class TreinoExercicioValuesObject {
	
	private Treino treino;
	private Exercicio exercicio;
	private Long ultimo;
	
	public TreinoExercicioValuesObject(Treino treino, Exercicio exercicio,Long ultimo) {
		this.treino = treino;
		this.exercicio = exercicio;
		this.ultimo = ultimo;
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
