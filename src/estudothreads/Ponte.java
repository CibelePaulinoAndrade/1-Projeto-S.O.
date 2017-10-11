package estudothreads;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.concurrent.Semaphore;

import enums.Direcao;
import enums.Prioridade;

public class Ponte{
	private static Ponte instancia = null;
	private Double tamanho;                     //Tamanho da ponte
	private Semaphore liberaPonte;              //Controla o acesso a ponte
	private Semaphore carro;                    //Número de carros na ponte
	private Semaphore mutex;                    //Controle de regiões "criticas"
	private Carro primeiroCarro;                //Primeiro carro 
	private Direcao direcaoPonte;               //Direção da ponte
	private Direcao prioridade;              //Prioridade da ponte
	private int aux;                            //Guarda o numero de carros do outro lado da ponte +1
	

	public Ponte(Double tamanho,Direcao prioridade) {
		super();
		this.tamanho = tamanho;
		this.primeiroCarro = null;
		this.direcaoPonte = Direcao.NENHUMA;
		this.prioridade = prioridade;
		this.liberaPonte = new Semaphore(1);
		this.carro = new Semaphore(0);
		this.mutex = new Semaphore(1);
		this.aux = 0;
		
	}
	public static void novaPonte(Double tamanho, Direcao prioridade){
		if(instancia == null){
			instancia = new Ponte(tamanho, prioridade);
		}
	}
	public static Ponte ponte(){
		return instancia;
	}

	public Double getTamanho() {
		return tamanho;
	}
	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}
	
	public Carro getPrimeiroCarro() {
		return primeiroCarro;
	}
	public void setPrimeiroCarro(Carro primeiroCarro) {
		this.primeiroCarro = primeiroCarro;
	}
	
	public Semaphore getLiberaPonte() {
		return liberaPonte;
	}
	public void setLiberaPonte(Semaphore liberaPonte) {
		this.liberaPonte = liberaPonte;
	}
	
	public Semaphore getCarro() {
		return carro;
	}
	public void setCarro(Semaphore carro) {
		this.carro = carro;
	}
	
	public Semaphore getMutex() {
		return mutex;
	}
	public void setMutex(Semaphore mutex) {
		this.mutex = mutex;
	}
	
	public Direcao getDirecaoPonte() {
		return direcaoPonte;
	}
	public void setDirecaoPonte(Direcao direcaoPonte) {
		this.direcaoPonte = direcaoPonte;
	}
	
	public Direcao getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Direcao prioridade) {
		this.prioridade = prioridade;
	}
	
	public int getAux() {
		return aux;
	}
	public void setAux(int aux) {
		this.aux = aux;
	}
}