package arvoreFeliz;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Movie> filmes = new ArrayList<Movie>();
		try {
			RandomAccessFile in = new RandomAccessFile(new File("movies.txt"),
					"r");
			String filme = in.readLine();
			ArrayList<String> atores = new ArrayList<String>();
			while (filme != null) {
				//
				String[] split = filme.split(";");
				for (int i = 1; i < split.length; i++) {
					atores.add(split[i]);
				}
				filmes.add(new Movie(split[0], atores));
				filme = in.readLine();
			}
			in.close();
		} catch (Exception err) {
			System.err.println(err.toString());
		}
		ArrayList<String> atores = new ArrayList<String>();
		for (Movie movie : filmes) {
			for (String ator : movie.getAtores()) {
				if (!atores.contains(ator)) {
					atores.add(ator);
				}
			}
		}
		System.err.println("Existem " + atores.size());
		System.err.println("Inicio");
		String atorAlvo = JOptionPane
				.showInputDialog("Escreva o nome do ator alvo!!");
		ArrayList<NoAtor> frontier = new ArrayList<NoAtor>();
		ArrayList<NoAtor> explored = new ArrayList<NoAtor>();
		ArrayList<NoAtor> filhos = new ArrayList<NoAtor>();

		NoAtor raiz = new NoAtor("Kevin Bacon", getFilhos("Kevin Bacon",
				filmes, frontier, explored, 0), 0);
		System.err.println("Raiz: " + raiz.toString());
		System.err.println("raiz tem"+raiz.getFilhos().size()+"filhos");
		explored.add(raiz);
		NoAtor noCorrente = null;
		boolean achou = false;
		while (frontier.size() > 0) {

			noCorrente = frontier.get(frontier.size() - 1);			
			frontier.remove(noCorrente);
			noCorrente.setFilhos(getFilhos(noCorrente.getNome(), filmes,
					frontier, explored, noCorrente.getProfundidade()));
			System.err.println("no corrente:" + noCorrente.toString());
			explored.add(noCorrente);
			for (NoAtor noAtor : filhos) {
				if (noAtor.getNome().equals(atorAlvo)) {
					achou = true;
					break;
				}
			}
		}
		System.err.println(achou);
	}

	public static ArrayList<NoAtor> getFilhos(String atorPai,
			ArrayList<Movie> movies, ArrayList<NoAtor> frontier,
			ArrayList<NoAtor> explored, int profundidadePai) {
		ArrayList<NoAtor> filhos = new ArrayList<NoAtor>();
		boolean jaAchado = false;
		for (Movie filme : movies) {
			if (filme.getAtores().contains(atorPai)) {
				jaAchado = false;
				for (String atorFilho : filme.getAtores()) {
					for (NoAtor noFrontier : frontier) {
						if (noFrontier.getNome().equals(atorFilho)) {
							jaAchado = true;
						}
					}
					for (NoAtor noExplored : explored) {
						if (noExplored.getNome().equals(atorFilho)) {
							jaAchado = true;
						}
					}
					for (NoAtor noFilho : filhos) {
						if (noFilho.getNome().equals(atorFilho)) {
							jaAchado = true;
						}
					}
					if (!jaAchado && !atorPai.equals(atorFilho)) {
						filhos.add(new NoAtor(atorFilho, null,
								profundidadePai + 1));
						frontier.add(new NoAtor(atorFilho, null,
								profundidadePai + 1));
					}
				}
			}
		}
		return filhos;

	}
}