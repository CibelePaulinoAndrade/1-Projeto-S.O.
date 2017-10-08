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
	public void criarCarro(Double tempoEspera, Double tempoTravessia, Estado estado, Caminho caminho){
		caminho.getCarros().add(
				new Carro(
						Ponte.ponte().getCaminhoDireita_Esquerda().getCarros().size() + Ponte.ponte().getCaminhoEsquerda_Direita().getCarros().size(),
						tempoEspera, 
						tempoTravessia, 
						estado,
						caminho
				)
		);
	}
	public void criarCarro(Double tempoEspera, Double tempoTravessia, Caminho caminho){
		caminho.getCarros().add(
				new Carro(
						Ponte.ponte().getCaminhoDireita_Esquerda().getCarros().size() + Ponte.ponte().getCaminhoEsquerda_Direita().getCarros().size(), 
						tempoEspera, 
						tempoTravessia, 
						caminho
				)
		);
	}
	public void printCarros(){
		ArrayList<Carro> arrayList = new ArrayList<Carro>();
		arrayList.addAll(Ponte.ponte().getCaminhoDireita_Esquerda().getCarros());
		arrayList.addAll(Ponte.ponte().getCaminhoEsquerda_Direita().getCarros());
		Collections.sort(arrayList, new Comparator<Carro>() {
			@Override
			public int compare(Carro o1, Carro o2) {
				return o1.getId() > o2.getId()? -1 : o1.getId() < o2.getId()?1:0;
			}
		});
		for(Carro carro:arrayList)
			System.out.print("ID: " + carro.getId() + " DIRECAO: " + carro.getCaminho().getCaminho() + " ESTADO: " + carro.getEstado() + " | ");
		System.out.println();
	}
	public void iniciarCarros(){
		for(Carro carro : Ponte.ponte().getCaminhoDireita_Esquerda().getCarros()){
			carro.start();
		}
		for(Carro carro : Ponte.ponte().getCaminhoEsquerda_Direita().getCarros()){
			carro.start();
		}
	}
	public void mudarDirecaoCarro(Carro carro){
		Caminho novo, velho;
		if(carro.getCaminho() == Ponte.ponte().getCaminhoDireita_Esquerda()){
			novo = Ponte.ponte().getCaminhoEsquerda_Direita();
			velho = Ponte.ponte().getCaminhoDireita_Esquerda();
		}
		else{
			novo = Ponte.ponte().getCaminhoDireita_Esquerda();
			velho = Ponte.ponte().getCaminhoEsquerda_Direita();
		}
		novo.getCarros().add(carro);
		velho.getCarros().remove(carro);
		carro.setCaminho(novo);
	}
	public Integer getMaximoCarros() {
		return maximoCarros;
	}
	public void setMaximoCarros(Integer maximoCarros) {
		this.maximoCarros = maximoCarros;
	}
}
