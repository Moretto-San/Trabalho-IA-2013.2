package arvoreFeliz;

import java.util.ArrayList;

public class NoAtor {
	private String nome = null;
	private ArrayList<NoAtor> filhos = null;

	public String getNome() {
		return nome;
	}

	public NoAtor(String nome, ArrayList<NoAtor> filhos) {
		super();
		this.nome = nome;
		this.filhos = filhos;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<NoAtor> getFilhos() {
		return filhos;
	}

	public void setFilhos(ArrayList<NoAtor> filhos) {
		this.filhos = filhos;
	}
}
