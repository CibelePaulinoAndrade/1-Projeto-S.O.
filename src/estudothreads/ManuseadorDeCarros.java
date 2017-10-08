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
	public void criarCarro(Double tempoEspera, Double tempoTravessia, Estado estado, Caminho caminho){
		carros.add(
			new Carro(
				carros.size(),
				tempoEspera, 
				tempoTravessia, 
				estado,
				caminho
			)
		);

	}
	public void criarCarro(Double tempoEspera, Double tempoTravessia, Caminho caminho){
		carros.add(
			new Carro(
					carros.size(),
					tempoEspera, 
					tempoTravessia, 
					caminho
			)
		);
	}
	public void iniciarCarros(){
		for(Carro carro : carros){
			carro.start();
		}
	}
	public void mudarDirecaoCarro(Carro carro){
		Caminho novo;
		if(carro.getCaminho() == Ponte.ponte().getCaminhoDireita_Esquerda()){
			novo = Ponte.ponte().getCaminhoEsquerda_Direita();
		}
		else{
			novo = Ponte.ponte().getCaminhoDireita_Esquerda();
		}
		carro.setCaminho(novo);
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
