package jogo;

public class Action {
	Botao origem;
	Botao destino;

	public Action(Botao origem, Botao destino) {
		this.origem = origem;
		this.destino = destino;
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
