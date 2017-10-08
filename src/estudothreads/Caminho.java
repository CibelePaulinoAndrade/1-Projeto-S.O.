package estudothreads;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Caminho extends Thread{
	private SemaforoNumeroCarrosFila semaforoNumeroCarrosFila;
	
	private List<Carro> carros;
	private Cancela cancela;
	private String caminho;
	
	
	public Caminho(Cancela cancela,
			String caminho) {
		this.semaforoNumeroCarrosFila = new SemaforoNumeroCarrosFila(0);
		this.cancela = cancela;
		this.caminho = caminho;
		this.carros = new ArrayList<Carro>();
	}
	public Caminho(List<Carro> carros, Cancela cancela, String caminho){
		this.semaforoNumeroCarrosFila = new SemaforoNumeroCarrosFila(0);
		this.carros = carros;
		this.cancela = cancela;
		this.caminho = caminho;
	}
	@Override
	public void run() {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("testando.txt", true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(true){
				//fileWriter.write("Caminho Travado");
				
				/*funcao do caminho eh sempre tentar ser a nova direcao*/
				semaforoNumeroCarrosFila.acquire(); //espera ate poder tentar ser a direcao da ponte, que é quando o primeiro carro chega na fila
				Ponte.ponte().getSemaforoLiberaCaminho().acquire(); //quando o primeiro carro chega, espera ate ser a nova direcao da ponte
				Ponte.ponte().setDirecao(this); //quando ele é a direcao da ponte, seta a direcao da ponte para ele mesmo
				//ponte liberada
				
				System.out.println(caminho + ": Caminho Liberado");
				//fileWriter.write("Caminho Liberado");
				
				semaforoNumeroCarrosFila.release();//eu aumento um espaço na ponte, para compensar o down
				cancela.getSemaforoCancelaLiberada().release();//libera a cancela para aceitar novos carros
				//System.out.println(cancela.getSemaforoCancelaLiberada().availablePermits());
				//Ponte.ponte().getSemaforoPonteMudaDirecao().release();//libera a ponte para mudar de direção
			}
		} catch (InterruptedException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public SemaforoNumeroCarrosFila getSemaforoNumeroCarrosFila() {
		return semaforoNumeroCarrosFila;
	}
	public void setSemaforoNumeroCarrosFila(SemaforoNumeroCarrosFila semaforoNumeroCarrosFila) {
		this.semaforoNumeroCarrosFila = semaforoNumeroCarrosFila;
	}
	public List<Carro> getCarros() {
		return carros;
	}
	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	public Cancela getCancela() {
		return cancela;
	}
	public void setCancela(Cancela cancela) {
		this.cancela = cancela;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public class SemaforoNumeroCarrosFila extends Semaphore{

		public SemaforoNumeroCarrosFila(int permits) {
			super(permits);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void reducePermits(int reduction) {
			// TODO Auto-generated method stub
			super.reducePermits(reduction);
		}
	}
}
