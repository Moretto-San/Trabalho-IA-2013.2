package arvoreFeliz;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

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
		ArrayList<NoAtor> frontier = new ArrayList<NoAtor>();
		ArrayList<NoAtor> explored = new ArrayList<NoAtor>();
		NoAtor raiz = new NoAtor("Kevin Bacon", getFilhos("Kevin Bacon",
				filmes, frontier));
		System.err.println(raiz.toString());
	}

	public static ArrayList<NoAtor> getFilhos(String atorPai,
			ArrayList<Movie> movies, ArrayList<NoAtor> frontier) {
		ArrayList<NoAtor> filhos = new ArrayList<NoAtor>();
		boolean jaAchado = false;
		for (Movie filme : movies) {
			if (filme.getAtores().contains(atorPai)) {
				for (String atorFilho : filme.getAtores()) {
					for (NoAtor noAtor : frontier) {
						if (noAtor.getNome().equals(atorFilho)) {
							jaAchado = true;
						}
					}
					if (!jaAchado && !atorPai.equals(atorFilho)) {
						filhos.add(new NoAtor(atorFilho, null));
						frontier.add(new NoAtor(atorFilho, null));
					}
				}
			}
		}
		return filhos;

	}
}