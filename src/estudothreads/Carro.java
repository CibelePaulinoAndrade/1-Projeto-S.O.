package estudothreads;

import java.io.FileWriter;
import java.io.IOException;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;


public class Carro extends Thread {
	private Integer id;
	private Double tempoEspera;
	private Double tempoTravessia;
	private LadoDaPonte ladodaPonte;
	private Direcao direcao;
	private Estado estado;
	//atributos usados so aqui
	private Double tempoEsperado;
	private Double tempoAtravessando;
	
	public Carro(Integer id, Double tempoEspera, Double tempoTravessia, LadoDaPonte ladodaPonte,
			Direcao direcao, Estado estado) {
		super();
		this.id = id;
		this.tempoEspera = tempoEspera;
		this.tempoTravessia = tempoTravessia;
		this.ladodaPonte = ladodaPonte;
		this.direcao = direcao;
		this.estado = estado;
	}
	
	public Carro(Integer id, Double tempoEspera, Double tempoTravessia, LadoDaPonte ladodaPonte,
			Direcao direcao) {
		super();
		this.id = id;
		this.tempoEspera = tempoEspera;
		this.tempoTravessia = tempoTravessia;
		this.ladodaPonte = ladodaPonte;
		this.direcao = direcao;
		this.estado = Estado.PARADO;
	}

	@Override
	public void run() {
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(Double tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public Double getTempoTravessia() {
		return tempoTravessia;
	}

	public void setTempoTravessia(Double tempoTravessia) {
		this.tempoTravessia = tempoTravessia;
	}

	public LadoDaPonte getLadodaPonte() {
		return ladodaPonte;
	}

	public void setLadodaPonte(LadoDaPonte ladodaPonte) {
		this.ladodaPonte = ladodaPonte;
	}

	public Direcao getDirecao() {
		return direcao;
	}

	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Double getTempoEsperado() {
		return tempoEsperado;
	}

	public void setTempoEsperado(Double tempoEsperado) {
		this.tempoEsperado = tempoEsperado;
	}
	
}
