package estudothreads;

import java.io.FileWriter;
import java.io.IOException;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;

//Comentando a classe Carro para testar se o compartilhamento deu certo.
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
		long tempoAnterior, tempoAtual;
		tempoAtual = 0;
		tempoAnterior = System.currentTimeMillis();
		tempoEsperado = tempoAtravessando = 0.0;
		Double tempoTravessiaAtual;
		FileWriter fileWriter = null;
		try {
			fileWriter  =  new FileWriter("teste.txt", true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		while(true){
			fileWriter.write("ID: " + id + " Direcao: "+direcao +" "+estado + " " + tempoEsperado +" " + tempoAtravessando+ " "+   "-\r\n");
			System.out.println("ID: " + id +" "+estado + " " + tempoAnterior + " " + tempoAtual);
			if(estado ==  Estado.DESTRUIDO)
				break;
			else if(estado == Estado.PARADO){
				tempoAtual = System.currentTimeMillis();
				tempoEsperado += tempoAtual - tempoAnterior;
				tempoAnterior = tempoAtual;
				if(tempoEsperado/1000 >= tempoEspera){	
					estado = Estado.AGUARDANDO;
					tempoEsperado = 0.0;
				}
			}
			else if(estado == Estado.AGUARDANDO){		
				if(Ponte.ponte().ponteLiberada(this)){
					Ponte.ponte().getSemaforoMesmaDirecao().acquire();
					estado = Estado.ATRAVESSANDO;
					tempoAtual = 0;
					tempoAnterior = System.currentTimeMillis();
					tempoAtravessando = 0.0;
					Ponte.ponte().setDirecao(direcao);
					if(Ponte.ponte().getPrimeiroCarro() == null){
						Ponte.ponte().setPrimeiroCarro(this);
					}
				}
			}
			else if(estado == Estado.ATRAVESSANDO){
				if(tempoTravessia > Ponte.ponte().getPrimeiroCarro().getTempoTravessia())
					tempoTravessiaAtual = Ponte.ponte().getPrimeiroCarro().getTempoTravessia();
				else
					tempoTravessiaAtual = tempoTravessia;
				tempoAtual = System.currentTimeMillis();
				tempoAtravessando += tempoAtual - tempoAnterior;
				tempoAnterior = tempoAtual;
				if(tempoAtravessando/1000 >= tempoTravessiaAtual){
					estado = Estado.PARADO;
					tempoAtual = 0;
					tempoAnterior = System.currentTimeMillis();
					direcao = direcao==Direcao.PARA_DIREITA? Direcao.PARA_ESQUERDA:Direcao.PARA_DIREITA;
					Ponte.ponte().getSemaforoMesmaDirecao().release();
					if(Ponte.ponte().getSemaforoMesmaDirecao().availablePermits()==ManuseadorDeCarros.manuseador().getMaximoCarros()){
						Ponte.ponte().setDirecao(Direcao.NENHUMA);
					}
				}	
			}
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
