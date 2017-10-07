package estudothreads;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Caminho{
	private Semaphore semaforoCarros;
	private List<Carro> carros;
	public Caminho(List<Carro> carros) {
		this.semaforoCarros = new Semaphore(ManuseadorDeCarros.manuseador().getMaximoCarros());
		this.carros = carros;
	}
	public Semaphore getSemaforoCarros() {
		return semaforoCarros;
	}
	public void setSemaforoCarros(Semaphore semaforoCarros) {
		this.semaforoCarros = semaforoCarros;
	}
	public List<Carro> getCarros() {
		return carros;
	}
	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	
}
