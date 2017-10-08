package estudothreads;

import java.util.ArrayList;
import java.util.List;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;

public class Principal {
	public static void main(String[] args) {
		Cancela cancelaDir = new Cancela();
		Caminho direita = new Caminho(cancelaDir, "DIREITA");
		cancelaDir.setCaminho(direita);
		
		Cancela cancelaEsq = new Cancela();
		Caminho esquerda = new Caminho(cancelaEsq, "ESQUERDA");
		cancelaEsq.setCaminho(esquerda);
		
		Ponte.novaPonte(direita, esquerda, 40.0);
		
		ManuseadorDeCarros.novoManuseador(10);
		ManuseadorDeCarros manuseador = ManuseadorDeCarros.manuseador();
		
		manuseador.criarCarro(3.0, 3.0, direita);
		manuseador.criarCarro(15.0, 15.0, direita);
		
		manuseador.iniciarCarros();
		Ponte.ponte().start();
		cancelaDir.start();
		cancelaEsq.start();
		direita.start();
		esquerda.start();
	}
}
