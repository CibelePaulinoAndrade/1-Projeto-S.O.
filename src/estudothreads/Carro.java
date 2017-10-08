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
	private Estado estado;
	private Caminho caminho;
	
	//atributos usados so aqui
	private Double tempoEsperado;
	private Double tempoAtravessando;
	
	public Carro(Integer id, Double tempoEspera, Double tempoTravessia,
			Estado estado, Caminho caminho) {
		super();
		this.id = id;
		this.tempoEspera = tempoEspera;
		this.tempoTravessia = tempoTravessia;
		this.estado = estado;
		this.caminho = caminho;
	}
	
	public Carro(Integer id, Double tempoEspera, Double tempoTravessia,
			Caminho caminho) {
		super();
		this.id = id;
		this.tempoEspera = tempoEspera;
		this.tempoTravessia = tempoTravessia;
		this.estado = Estado.PARADO;
		this.caminho = caminho;
	}

	@Override
	public void run() {
		double tempoAnterior, tempoAtual;
		tempoAtual = 0;
		tempoAnterior = System.currentTimeMillis();
		tempoEsperado = tempoAtravessando = 0.0;
		Double tempoTravessiaAtual;
		try {
			while(true){
				if(estado == Estado.PARADO){//conta tempo parado e depois vai para aguardando
					tempoAtual = System.currentTimeMillis();
					tempoEsperado += tempoAtual - tempoAnterior;
					tempoAnterior = tempoAtual;
					if(tempoEsperado/1000 >= tempoEspera){
						Log.doLog(ManuseadorDeCarros.manuseador().getCarros());
						estado = Estado.AGUARDANDO;
						tempoEsperado = 0.0;
					}
				}
				else if(estado == Estado.AGUARDANDO){
				
					caminho.getSemaforoNumeroCarrosFila().release();//aumenta numero de carros na fila
					
					Log.doLog(caminho.getCaminho()+": NUMERO CARROS NA FILA: " + caminho.getSemaforoNumeroCarrosFila().availablePermits());
					Log.doLog(ManuseadorDeCarros.manuseador().getCarros());
					caminho.getCancela().getSemaforoNumeroCarrosPodemAtravessar().acquire();//espera ate que a cancela deixe-o passar
					estado = Estado.ATRAVESSANDO;	//se passou muda estado para ateavessando 
					Log.doLog(ManuseadorDeCarros.manuseador().getCarros());
					tempoAtravessando = 0.0; 
					tempoAtual = 0.0;
					tempoAnterior = System.currentTimeMillis();
				}
				else if(estado == Estado.ATRAVESSANDO){ //conta tempo de travessia e depois fica parado
					tempoAtual = System.currentTimeMillis();
					tempoAtravessando += tempoAtual - tempoAnterior;
					tempoAnterior = tempoAtual;
					if(tempoAtravessando/1000 >= tempoTravessia){	//trocar isso
						Log.doLog(ManuseadorDeCarros.manuseador().getCarros());
						estado = Estado.PARADO;//muda estado
						ManuseadorDeCarros.manuseador().mudarDirecaoCarro(this);//muda direcoa do carro
						tempoEsperado = 0.0;
						tempoAtual = 0.0;
						tempoAnterior = System.currentTimeMillis();
						caminho.setnCarrosAtravessando(caminho.getnCarrosAtravessando() - 1);
						if(caminho.getnCarrosAtravessando() == 0){
							caminho.getCancela().getSemaforoCancelaLiberada().reducePermits(1);
							Ponte.ponte().getSemaforoLiberaCaminho().release(); //libera um caminho para ser a nova direção
						}
					}
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Caminho getCaminho() {
		return caminho;
	}

	public void setCaminho(Caminho caminho) {
		this.caminho = caminho;
	}
	
}
