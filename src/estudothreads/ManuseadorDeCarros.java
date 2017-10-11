package estudothreads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;

public class ManuseadorDeCarros {
	private static ManuseadorDeCarros instancia = null;
	private Integer maximoCarros;
	private List<Carro> carros;
	
	private ManuseadorDeCarros(Integer maximoCarros){
		this.maximoCarros = maximoCarros;
		this.carros = new ArrayList<Carro>();
	}
	public static void novoManuseador(Integer maximoCarros){
		if(instancia==null){
			instancia =  new ManuseadorDeCarros(maximoCarros);
		}
	}
	public static ManuseadorDeCarros manuseador(){
		return instancia;
	}
	public void criarCarro(Double tempoEspera, Double tempoTravessia, Estado estado, Direcao direcaoCarro){
		carros.add(
			new Carro(
				carros.size(),
				tempoEspera, 
				tempoTravessia, 
				estado,
				direcaoCarro
			)
		);

	}
	public void criarCarro(Double tempoEspera, Double tempoTravessia, Direcao direcaoCarro){
		carros.add(
			new Carro(
					carros.size(),
					tempoEspera, 
					tempoTravessia, 
					direcaoCarro
			)
		);
	}
	public void iniciarCarros(){
		for(Carro carro : carros){
			carro.start();
		}
	}
	public void mudarDirecaoCarro(Carro carro){
		Direcao nova;
		if(carro.getDirecaoCarro() == Direcao.PARA_DIREITA){
			nova = Direcao.PARA_ESQUERDA;
		}
		else{
			nova = Direcao.PARA_DIREITA;
		}
		carro.setDirecaoCarro(nova);
		System.out.println("Mudou "+nova);
	}
	public Integer getMaximoCarros() {
		return maximoCarros;
	}
	public void setMaximoCarros(Integer maximoCarros) {
		this.maximoCarros = maximoCarros;
	}
	public List<Carro> getCarros() {
		return carros;
	}
	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	
}
