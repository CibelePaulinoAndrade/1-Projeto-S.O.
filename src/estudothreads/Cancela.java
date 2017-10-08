package estudothreads;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Cancela extends Thread {
	private Caminho caminho;
	private SemaforoCancelaLiberada semaforoCancelaLiberada;
	private Semaphore semaforoNumeroCarrosPodemAtravessar;
	public Cancela() {
		super();
		semaforoCancelaLiberada = new SemaforoCancelaLiberada(0);
		semaforoNumeroCarrosPodemAtravessar = new Semaphore(0);
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
			int numeroCarrosFila = 0;
			while(true){/*
				semaforoCancelaLiberada.acquire();//espera ate que possa libear o caminho dos carros
				numeroCarrosFila = caminho.getSemaforoNumeroCarrosFila().availablePermits();
				semaforoNumeroCarrosPodemAtravessar.release(numeroCarrosFila);//libera o caminho de todos os carros que estao esperando
				caminho.getSemaforoNumeroCarrosFila().reducePermits(numeroCarrosFila);
				semaforoCancelaLiberada.release();*/
				semaforoCancelaLiberada.acquire();//espera ate que possa libear o caminho dos carros
				caminho.getSemaforoNumeroCarrosFila().acquire();
				semaforoNumeroCarrosPodemAtravessar.release();//libera o caminho de um carro
				semaforoCancelaLiberada.release();//libera ela propria para pegar novos carros
				
				//fileWriter.write("Cancela Liberada");
				
			}
		} catch (InterruptedException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public SemaforoCancelaLiberada getSemaforoCancelaLiberada() {
		return semaforoCancelaLiberada;
	}
	public void setSemaforoCancelaLiberada(SemaforoCancelaLiberada semaforoCancelaLiberada) {
		this.semaforoCancelaLiberada = semaforoCancelaLiberada;
	}
	public Semaphore getSemaforoNumeroCarrosPodemAtravessar() {
		return semaforoNumeroCarrosPodemAtravessar;
	}
	public void setSemaforoNumeroCarrosPodemAtravessar(
			Semaphore semaforoNumeroCarrosPodemAtravessar) {
		this.semaforoNumeroCarrosPodemAtravessar = semaforoNumeroCarrosPodemAtravessar;
	}
	public class SemaforoCancelaLiberada extends Semaphore{

		public SemaforoCancelaLiberada(int permits) {
			super(permits);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void reducePermits(int reduction) {
			// TODO Auto-generated method stub
			super.reducePermits(reduction);
		}
	}
	public Caminho getCaminho() {
		return caminho;
	}
	public void setCaminho(Caminho caminho) {
		this.caminho = caminho;
	}
	
}
