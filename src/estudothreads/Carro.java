package estudothreads;

import java.io.FileWriter;
import java.io.IOException;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;


public class Carro extends Thread {
	private Integer id;
	private Double tempoEspera;                 //Tempo de espera
	private Double tempoTravessia;              //tempo de travessia
	private Estado estado;                      //Estado do carro
	private Direcao direcaoCarro;               //Dire��o do carro
	
	//atributos usados so aqui
	private Double tempoEsperado;
	private Double tempoAtravessando;
	
	public Carro(Integer id, Double tempoEspera, Double tempoTravessia,
			Estado estado, Direcao direcaoCarro) {
		super();
		this.id = id;
		this.tempoEspera = tempoEspera;
		this.tempoTravessia = tempoTravessia;
		this.estado = estado;
		this.direcaoCarro = direcaoCarro;
	}
	
	public Carro(Integer id, Double tempoEspera, Double tempoTravessia,
			Direcao direcaoCarro) {
		super();
		this.id = id;
		this.tempoEspera = tempoEspera;
		this.tempoTravessia = tempoTravessia;
		this.estado = Estado.PARADO;
		this.direcaoCarro = direcaoCarro;
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
					Ponte.ponte().getMutex().acquire();
					if((Ponte.ponte().getDirecaoPonte()== Direcao.NENHUMA)||(direcaoCarro != Ponte.ponte().getDirecaoPonte())) {
						Ponte.ponte().setAux(Ponte.ponte().getAux() +1); 
						Ponte.ponte().getMutex().release();
						Log.doLog(ManuseadorDeCarros.manuseador().getCarros());
						Ponte.ponte().setDirecaoPonte(direcaoCarro);
						Ponte.ponte().getLiberaPonte().acquire();
						Ponte.ponte().getMutex().acquire();
					}
					Ponte.ponte().getMutex().release();
					Ponte.ponte().getCarro().release();
					estado = Estado.ATRAVESSANDO;	//se passou muda estado para atravessando
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
						Ponte.ponte().getCarro().acquire();
						estado = Estado.PARADO;//muda estado
						Ponte.ponte().getMutex().acquire();
						if(Ponte.ponte().getCarro().availablePermits() == 0) {
							Ponte.ponte().getLiberaPonte().release(Ponte.ponte().getAux());
							Ponte.ponte().setDirecaoPonte(Direcao.NENHUMA);
						}
						Ponte.ponte().getMutex().release();
						ManuseadorDeCarros.manuseador().mudarDirecaoCarro(this);//muda direcoa do carro
						tempoEsperado = 0.0;
						tempoAtual = 0.0;
						tempoAnterior = System.currentTimeMillis();
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

	public Direcao getDirecaoCarro() {
		return direcaoCarro;
	}
	public void setDirecaoCarro(Direcao direcaoCarro) {
		this.direcaoCarro = direcaoCarro;
	}

	public Double getTempoAtravessando() {
		return tempoAtravessando;
	}
	public void setTempoAtravessando(Double tempoAtravessando) {
		this.tempoAtravessando = tempoAtravessando;
	}
	
}
