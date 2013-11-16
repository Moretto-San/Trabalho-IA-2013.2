package arvoreFeliz;

import java.util.ArrayList;

public class Movie {
	private String nome = null;
	private ArrayList<String> atores = null;

	public Movie(String nome, ArrayList<String> atores) {
		super();
		this.nome = nome;
		this.atores = atores;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Movie [nome=" + nome + ", atores=" + atores + "]";
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<String> getAtores() {
		return atores;
	}

	public void setAtores(ArrayList<String> atores) {
		this.atores = atores;
	}
}