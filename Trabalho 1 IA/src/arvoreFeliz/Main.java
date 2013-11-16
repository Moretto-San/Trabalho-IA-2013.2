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
	}
}
