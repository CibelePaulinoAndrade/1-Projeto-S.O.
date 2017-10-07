package estudothreads;

import java.util.ArrayList;
import java.util.List;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;

public class ManuseadorDeCarros {
	private static ManuseadorDeCarros instancia = null;
	private Integer maximoCarros;
	
	private ManuseadorDeCarros(Integer maximoCarros){
		this.maximoCarros = maximoCarros;
	}
	public static void novoManuseador(Integer maximoCarros){
		if(instancia==null){
			instancia =  new ManuseadorDeCarros(maximoCarros);
		}
	}
	public static ManuseadorDeCarros manuseador(){
		return instancia;
	}
	public void criarCarro(Double tempoEspera, Double tempoTravessia, LadoDaPonte ladodaPonte, Direcao direcao, Estado estado, Caminho caminho){
		caminho.getCarros().add(
				new Carro(
						Ponte.ponte().getCaminhoDireita_Esquerda().getCarros().size() + Ponte.ponte().getCaminhoEsquerda_Direita().getCarros().size(),
						tempoEspera, 
						tempoTravessia, 
						ladodaPonte, 
						direcao, 
						estado
				)
		);
	}
	public void criarCarro(Double tempoEspera, Double tempoTravessia, LadoDaPonte ladodaPonte, Direcao direcao, Caminho caminho){
		caminho.getCarros().add(
				new Carro(
						Ponte.ponte().getCaminhoDireita_Esquerda().getCarros().size() + Ponte.ponte().getCaminhoEsquerda_Direita().getCarros().size(), 
						tempoEspera, 
						tempoTravessia, 
						ladodaPonte, 
						direcao
				)
		);
	}
	public void iniciarCarros(){
		for(Carro carro : Ponte.ponte().getCaminhoDireita_Esquerda().getCarros()){
			carro.start();
		}
		for(Carro carro : Ponte.ponte().getCaminhoEsquerda_Direita().getCarros()){
			carro.start();
		}
	}
	public Integer getMaximoCarros() {
		return maximoCarros;
	}
	public void setMaximoCarros(Integer maximoCarros) {
		this.maximoCarros = maximoCarros;
	}
}
