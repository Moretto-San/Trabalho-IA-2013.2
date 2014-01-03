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
				filmes.add(new Movie(split[0], (ArrayList<String>) atores
						.clone()));
				atores.clear();
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
		System.err.println("Existem " + atores.size() + "atores");
		System.err.println("Existem " + filmes.size() + "filmes");
		String tipoBusca = JOptionPane
				.showInputDialog("Digite 1 para Busca em Profundidade ou 2 para busca em profundidade iterativa!!!");
		String atorAlvo = JOptionPane
				.showInputDialog("Escreva o nome do ator alvo!!");
		if (tipoBusca.equals("1")) {
			buscaProfundidade(6, atorAlvo, filmes);
		} else {
			for (int i = 1; i < 6; i++) {
				System.err.println("Profundiade: " + i);
				NoAtor alvo = buscaProfundidade(i, atorAlvo, filmes);
				if (alvo != null && alvo.getNome().equals(atorAlvo)) {
					break;
				}
			}
		}
	}

	public static NoAtor buscaProfundidade(int profundidade, String atorAlvo,
			ArrayList<Movie> filmes) {
		NoAtor alvo = null;
		ArrayList<NoAtor> frontier = new ArrayList<NoAtor>();
		ArrayList<NoAtor> explored = new ArrayList<NoAtor>();
		NoAtor raiz = new NoAtor("Kevin Bacon", null, 0);
		frontier.add(raiz);
		NoAtor noCorrente = null;
		while (frontier.size() > 0) {
			noCorrente = frontier.get(frontier.size() - 1);
			if (noCorrente.getProfundidade() <= profundidade-1) {
				frontier.remove(noCorrente);
				noCorrente.setFilhos(getFilhos(noCorrente.getNome(), filmes,
						frontier, explored, noCorrente.getProfundidade()));
				explored.add(noCorrente);
				for (NoAtor noAtor : noCorrente.getFilhos()) {
					if (noAtor.getNome().equals(atorAlvo)) {
						alvo = noAtor;
						break;
					}
				}
			} else {
				frontier.remove(noCorrente);
				explored.add(noCorrente);
			}
		}
		System.err.println("alvo: " + alvo);
		return alvo;
	}

	public static ArrayList<NoAtor> getFilhos(String atorPai,
			ArrayList<Movie> movies, ArrayList<NoAtor> frontier,
			ArrayList<NoAtor> explored, int profundidadePai) {
		ArrayList<NoAtor> filhos = new ArrayList<NoAtor>();
		boolean jaAchado = false;
		for (Movie filme : movies) {
			if (filme.getAtores().contains(atorPai)) {
				for (String atorFilho : filme.getAtores()) {
					jaAchado = false;
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