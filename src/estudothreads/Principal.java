package estudothreads;

import java.util.ArrayList;
import java.util.List;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;

public class Principal {
	public static void main(String[] args) {
		
		Direcao direita = Direcao.PARA_DIREITA;
		Direcao esquerda = Direcao.PARA_ESQUERDA;
		
		Ponte.novaPonte(50.0, direita);
		
		ManuseadorDeCarros.novoManuseador(10);
		ManuseadorDeCarros manuseador = ManuseadorDeCarros.manuseador();
		
		manuseador.criarCarro(5.0, 9.0, direita);
		manuseador.criarCarro(7.0, 4.0, direita);
		
		manuseador.iniciarCarros();
	}
}
