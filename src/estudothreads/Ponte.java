package estudothreads;

import java.util.List;
import java.util.concurrent.Semaphore;

import enums.Direcao;
import enums.Prioridade;

public class Ponte{
	private static Ponte instancia = null;
	private Prioridade prioridade;
	private Direcao direcao;
	private Double tamanho;
	private Carro ultimoCarro;
	private Semaphore semaforoMesmaDirecao;
	private Carro primeiroCarro;
	public Ponte(Prioridade prioridade, Direcao direcao, Double tamanho){
		super();
		this.prioridade = prioridade;
		this.direcao = direcao;
		this.tamanho = tamanho;
		this.primeiroCarro = null;
		this.ultimoCarro = null;
		this.semaforoMesmaDirecao = new Semaphore(ManuseadorDeCarros.manuseador().getMaximoCarros());
	}
	public Ponte(Direcao direcao, Double tamanho) {
		super();
		this.prioridade = Prioridade.NENHUMA;
		this.direcao = direcao;
		this.tamanho = tamanho;
		this.primeiroCarro = null;
		this.ultimoCarro = null;
		this.semaforoMesmaDirecao = new Semaphore(ManuseadorDeCarros.manuseador().getMaximoCarros());
	}
	public static void novaPonte(Direcao direcao, Double tamanho){
		if(instancia == null){
			instancia = new Ponte(direcao, tamanho);
		}
	}
	public static void novaPonte(Prioridade prioridade, Direcao direcao, Double tamanho){
		if(instancia == null){
			instancia = new Ponte(prioridade, direcao, tamanho);
		}
	}
	public Boolean ponteLiberada(Carro carro){
		return carro.getDirecao() == direcao || direcao == Direcao.NENHUMA;
	}
	public static Ponte ponte(){
		return instancia;
	}
	public Semaphore getSemaforoMesmaDirecao() {
		return semaforoMesmaDirecao;
	}
	public void setSemaforoMesmaDirecao(Semaphore semaforoMesmaDirecao) {
		this.semaforoMesmaDirecao = semaforoMesmaDirecao;
	}
	public Prioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
	public Direcao getDirecao() {
		return direcao;
	}
	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}
	public Double getTamanho() {
		return tamanho;
	}
	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}
	public Carro getUltimoCarro() {
		return ultimoCarro;
	}
	public void setUltimoCarro(Carro ultimoCarro) {
		this.ultimoCarro = ultimoCarro;
	}
	public Carro getPrimeiroCarro() {
		return primeiroCarro;
	}
	public void setPrimeiroCarro(Carro primeiroCarro) {
		this.primeiroCarro = primeiroCarro;
	}
	
	
}