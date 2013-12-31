package arvoreFeliz;

import java.util.ArrayList;

public class NoAtor {
	private String nome = null;
	private ArrayList<NoAtor> filhos = null;
	private int profundidade = 0;

	public String getNome() {
		return nome;
	}

	public NoAtor(String nome, ArrayList<NoAtor> filhos, int profundidade) {
		super();
		this.nome = nome;
		this.filhos = filhos;
		this.profundidade = profundidade;
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

	@Override
	public String toString() {
		return "[nome=" + nome + ", filhos=" + filhos + "profundidade: "+ profundidade + "]";
	}

	public int getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}
}