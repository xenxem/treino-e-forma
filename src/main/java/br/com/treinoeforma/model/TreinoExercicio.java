package br.com.treinoeforma.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TreinoExercicio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="treino_id")
	private Treino treino;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="exercicio_id")
	private Exercicio exercicio;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="titulo_id")	
	private Titulo titulo;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar data;
		
	
	private Long ordem;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Long getOrdem() {
		return ordem;
	}
	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}
	
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
		
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
	

}
