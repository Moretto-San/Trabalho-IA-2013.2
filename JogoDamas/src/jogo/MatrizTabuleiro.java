package jogo;

import java.io.Serializable;

public class MatrizTabuleiro implements Serializable{
	
	private Botao tabuleiro[][];
	private static final long serialVersionUID = 1L;
	
	public MatrizTabuleiro(Botao tabuleiro[][]){
		this.tabuleiro = tabuleiro;
	}

	public Botao[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Botao[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
