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
	private Double tamanho;
	private Semaphore semaforoLiberaCaminho;
	private Carro primeiroCarro;
	private Caminho direcao;
	private Caminho caminhoDireita_Esquerda;
	private Caminho caminhoEsquerda_Direita;
	public Ponte(Caminho caminhoDireita_Esquerda, Caminho caminhoEsquerda_Direita, Double tamanho){
		super();
		this.direcao = null;
		this.tamanho = tamanho;
		this.primeiroCarro = null;
		this.semaforoLiberaCaminho = new Semaphore(1);//no começo qualquer caminho pode ser a direcao
		this.caminhoDireita_Esquerda = caminhoDireita_Esquerda;
		this.caminhoEsquerda_Direita = caminhoEsquerda_Direita;
	}
	public static void novaPonte(Caminho caminhoDireita_Esquerda, Caminho caminhoEsquerda_Direita, Double tamanho){
		if(instancia == null){
			instancia = new Ponte(caminhoDireita_Esquerda, caminhoEsquerda_Direita, tamanho);
		}
	}
	public static Ponte ponte(){
		return instancia;
	}
	public Caminho getDirecao() {
		return direcao;
	}
	public void setDirecao(Caminho direcao) {
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
	public Semaphore getSemaforoLiberaCaminho() {
		return semaforoLiberaCaminho;
	}
	public void setSemaforoLiberaCaminho(Semaphore semaforoLiberaCaminho) {
		this.semaforoLiberaCaminho = semaforoLiberaCaminho;
	}
}