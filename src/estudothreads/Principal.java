package estudothreads;

import java.util.ArrayList;
import java.util.List;

import enums.Direcao;
import enums.Estado;
import enums.LadoDaPonte;

public class Principal {

	public static void main(String[] args) {
		ManuseadorDeCarros.novoManuseador(10);
		Ponte.novaPonte(Direcao.NENHUMA, 20.0);
		ManuseadorDeCarros manuseadorDeCarros = ManuseadorDeCarros.manuseador();
		for(int i=0; i<2; i++)
			manuseadorDeCarros.criarCarro(4.0, 4.0, LadoDaPonte.ESQUERDA, Direcao.PARA_DIREITA);
		manuseadorDeCarros.criarCarro(4.0, 4.0, LadoDaPonte.DIREITA, Direcao.PARA_ESQUERDA);
		manuseadorDeCarros.iniciarCarros();
	}
}
