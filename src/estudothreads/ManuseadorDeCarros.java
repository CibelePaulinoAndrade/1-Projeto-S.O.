package estudothreads;

import java.util.ArrayList;
import java.util.List;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;

public class ManuseadorDeCarros {
	private static ManuseadorDeCarros instancia = null;
	private List<Carro> carros;
	private Integer maximoCarros;
	
	private ManuseadorDeCarros(List<Carro> carros, Integer maximoCarros){
		this.carros = carros;
		this.maximoCarros = maximoCarros;
	}
	private ManuseadorDeCarros(Integer maximoCarros){
		this.carros = new ArrayList<Carro>();
		this.maximoCarros = maximoCarros;
	}
	public static void novoManuseador(List<Carro> carros, Integer maximoCarros){
		if(instancia==null){
			instancia = new ManuseadorDeCarros(carros, maximoCarros);
		}
	}
	public static void novoManuseador(Integer maximoCarros){
		if(instancia==null){
			instancia =  new ManuseadorDeCarros(maximoCarros);
		}
	}
	public static ManuseadorDeCarros manuseador(){
		return instancia;
	}
	public void criarCarro(Double tempoEspera, Double tempoTravessia, LadoDaPonte ladodaPonte, Direcao direcao, Estado estado, Boolean vivo){
		carros.add(new Carro(carros.size(), tempoEspera, tempoTravessia, ladodaPonte, direcao, estado));
	}
	public void criarCarro(Double tempoEspera, Double tempoTravessia, LadoDaPonte ladodaPonte, Direcao direcao){
		carros.add(new Carro(carros.size(), tempoEspera, tempoTravessia, ladodaPonte, direcao));
	}
	public void iniciarCarros(){
		for(Carro carro : carros){
			carro.start();
		}
	}
	public List<Carro> getCarros() {
		return carros;
	}
	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	public Integer getMaximoCarros() {
		return maximoCarros;
	}
	public void setMaximoCarros(Integer maximoCarros) {
		this.maximoCarros = maximoCarros;
	}
}
