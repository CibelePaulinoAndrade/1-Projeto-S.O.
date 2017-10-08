package estudothreads;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.concurrent.Semaphore;

import enums.Direcao;
import enums.Prioridade;

public class Ponte extends Thread{
	private static Ponte instancia = null;
	private Double tamanho;
	private SemaforoPonteMudaDirecao semaforoPonteMudaDirecao;
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
		this.semaforoPonteMudaDirecao = new SemaforoPonteMudaDirecao(0); //no começo a ponte nao pode mudar a direcao
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
	@Override
	public void run() {
		/*objetivo da ponte eh sempre tentar mudar a direcao da ponte*/
		try {
			while(true){
				
				semaforoPonteMudaDirecao.acquire(); //espera ate poder mudar de direcao
				semaforoLiberaCaminho.release(); //libera um caminho para ser a nova direção

				direcao.getCancela().getSemaforoCancelaLiberada().reducePermits(1);
				
				System.out.println("PONTE Mudança Direcao :" + direcao.getCaminho());
			}
		} catch (InterruptedException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public SemaforoPonteMudaDirecao getSemaforoPonteMudaDirecao() {
		return semaforoPonteMudaDirecao;
	}
	public void setSemaforoPonteMudaDirecao(SemaforoPonteMudaDirecao semaforoPonteMudaDirecao) {
		this.semaforoPonteMudaDirecao = semaforoPonteMudaDirecao;
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
	
	class SemaforoPonteMudaDirecao extends Semaphore{

		public SemaforoPonteMudaDirecao(int permits) {
			super(permits);
		}
		@Override
		public void reducePermits(int reduction) {
			super.reducePermits(reduction);
		}
	}
}