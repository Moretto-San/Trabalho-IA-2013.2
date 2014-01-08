package jogo;

public class Action {
	Botao origem;
	Botao destino;
	Botao[][] tabuleiro;

	public Action(Botao origem, Botao destino, Botao[][] tabuleiro) {
		this.origem = origem;
		this.destino = destino;
		this.tabuleiro = tabuleiro;
	}

	public Botao[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Botao[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Botao getOrigem() {
		return origem;
	}

	public void setOrigem(Botao origem) {
		this.origem = origem;
	}

	public Botao getDestino() {
		return destino;
	}

	public void setDestino(Botao destino) {
		this.destino = destino;
	}
}
