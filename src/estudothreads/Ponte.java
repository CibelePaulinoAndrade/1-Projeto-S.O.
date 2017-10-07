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
	private Semaphore semaforoBooleano;
	private Carro primeiroCarro;
	private Caminho caminhoDireita_Esquerda;
	private Caminho caminhoEsquerda_Direita;
	public Ponte(Prioridade prioridade, Direcao direcao, Double tamanho){
		super();
		this.prioridade = prioridade;
		this.direcao = direcao;
		this.tamanho = tamanho;
		this.primeiroCarro = null;
	}
	public Ponte(Direcao direcao, Double tamanho) {
		super();
		this.prioridade = Prioridade.NENHUMA;
		this.direcao = direcao;
		this.tamanho = tamanho;
		this.primeiroCarro = null;
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
	public Carro getPrimeiroCarro() {
		return primeiroCarro;
	}
	public void setPrimeiroCarro(Carro primeiroCarro) {
		this.primeiroCarro = primeiroCarro;
	}
	public Semaphore getSemaforoBooleano() {
		return semaforoBooleano;
	}
	public void setSemaforoBooleano(Semaphore semaforoBooleano) {
		this.semaforoBooleano = semaforoBooleano;
	}
	public Caminho getCaminhoDireita_Esquerda() {
		return caminhoDireita_Esquerda;
	}
	public void setCaminhoDireita_Esquerda(Caminho caminhoDireita_Esquerda) {
		this.caminhoDireita_Esquerda = caminhoDireita_Esquerda;
	}
	public Caminho getCaminhoEsquerda_Direita() {
		return caminhoEsquerda_Direita;
	}
	public void setCaminhoEsquerda_Direita(Caminho caminhoEsquerda_Direita) {
		this.caminhoEsquerda_Direita = caminhoEsquerda_Direita;
	}
	
}