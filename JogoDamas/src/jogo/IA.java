package jogo;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class IA {
	private Cor corPecaIa = Cor.VERMELHA;
	private Cor minhaPeca;
	private ArrayList<Botao> botoesASeremComidos = new ArrayList<Botao>();
	private int index = -1;
	private ImageIcon icon1 = new ImageIcon(
			Tabuleiro.class.getResource("/img/circle-blue.png")); // Peça Azul
	private ImageIcon icon2 = new ImageIcon(
			Tabuleiro.class.getResource("/img/circle-red.png")); // Peça
																	// Vermelha
	private Botao[][] tabuleiroRespostaIa;

	public IA() {

	}

	public Botao[][] ALPHABETASEARCH(Botao[][] tabuleiroIn) {
		tabuleiroRespostaIa = null;
		Botao[][] tabuleiro = clonaTabuleiro(tabuleiroIn);
		MAXVALUE(tabuleiro, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
		return tabuleiroRespostaIa;
	}

	public int MAXVALUE(Botao[][] tabuleiroIn, int alfa, int beta,
			int profundidade) {
		Botao[][] tabuleiro = clonaTabuleiro(tabuleiroIn);
		System.err.println("Profundidade: "+profundidade);
		corPecaIa = Cor.VERMELHA;
		Botao[][] tabuleiroTemp = null;
		ArrayList<Action> actions = getActions(tabuleiro);
		int v = Integer.MIN_VALUE;
		if (verificarPorVencedor(tabuleiro) > 0 || profundidade == 2) {
			return verificarPorVencedor(tabuleiro);
		}
		for (Action action : actions) {
			tabuleiroTemp = result(action);
			int aux = MINVALUE(tabuleiroTemp, alfa, beta, profundidade + 1);
			if (aux > v) {
				v = aux;
				if (profundidade == 0) {
					tabuleiroRespostaIa = clonaTabuleiro(tabuleiroTemp);
				}
			}
			if (v >= beta) {
				return v;
			}
			if (v > alfa) {
				alfa = v;
			}
		}
		return v;
	}
	
	

	public int MINVALUE(Botao[][] tabuleiroIn, int alfa, int beta,
			int profundidade) {
		Botao[][] tabuleiro = clonaTabuleiro(tabuleiroIn);
		System.err.println("Profundidade: "+profundidade);

		corPecaIa = Cor.AZUL;
		Botao[][] tabuleiroTemp = null;
		ArrayList<Action> actions = getActions(tabuleiro);
		int v = Integer.MAX_VALUE;
		if (verificarPorVencedor(tabuleiro) < 0 || profundidade == 2) {
			return verificarPorVencedor(tabuleiro);
		}
		for (Action action : actions) {
			tabuleiroTemp = clonaTabuleiro(result(action));
			int aux = MAXVALUE(tabuleiroTemp, alfa, beta, profundidade + 1);
			if (aux < v) {
				v = aux;
			}
			if (v <= alfa) {
				return v;
			}
			if (v < beta) {
				beta = v;
			}
		}
		return v;
	}

	public int verificarPorVencedor(Botao[][] tabuleiro) {
		int contadorPecasVermelhas = 0;
		int contadorPecasAzuis = 0;
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (tabuleiro[i][j].getCor() == Cor.VERMELHA)
					contadorPecasVermelhas++;
				else if (tabuleiro[i][j].getCor() == Cor.AZUL)
					contadorPecasAzuis++;
			}
		}
		if (contadorPecasAzuis == 0) {
			return contadorPecasVermelhas;
		} else if (contadorPecasVermelhas == 0) {
			return -contadorPecasAzuis;
		}
		return contadorPecasVermelhas - contadorPecasAzuis;
	}

	public Botao[][] result(Action acao) {
		Botao[][] tabuleiro = acao.getTabuleiro();
		if (acao.getDestino().getBackground() == Color.GREEN) {
			imprimiTabuleiro(tabuleiro);
			System.err.println("Origem: I = " + acao.getOrigem().getI()
					+ " J = " + acao.getOrigem().getJ() + " Destino: I = "
					+ acao.getDestino().getI() + " J = "
					+ acao.getDestino().getJ() + " Cor: "
					+ acao.getDestino().getBackground());
			comerPeca(					
					procurarPecaEscolhidaParaComer(acao),
					acao.getOrigem(), acao.getDestino(), tabuleiro);
		}
		if (acao.getDestino().getBackground() == Color.RED) {
			moverPeca(acao.getOrigem(), acao.getDestino(), tabuleiro);
		}
		return tabuleiro;
	}
	public Botao[][] clonaTabuleiro(Botao[][] tabuleiroIn){
		Botao[][] tabuleiro = new Botao[8][8];
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				tabuleiro[i][j] = new Botao(tabuleiroIn[i][j].getI(), tabuleiroIn[i][j].getJ(), tabuleiroIn[i][j].getCor());
				tabuleiro[i][j].setEmRisco(tabuleiroIn[i][j].isEmRisco());
				tabuleiro[i][j].setBackground(tabuleiroIn[i][j].getBackground());
			}
			}
		return tabuleiro;
	}
	public ArrayList<Action> getActions(Botao[][] tabuleiro) {
		ArrayList<Action> actions = new ArrayList<Action>();
		Botao origem = null;
		Botao destino = null;
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				if (tabuleiro[i][j].getCor().equals(corPecaIa)) {
					mostrarOpcoes(tabuleiro[i][j], tabuleiro);
					for (int k = 0; k < tabuleiro.length; k++) {
						for (int l = 0; l < tabuleiro.length; l++) {
							if (tabuleiro[k][l].getBackground() == Color.GREEN
									|| tabuleiro[k][l].getBackground() == Color.RED) {
								origem = new Botao(tabuleiro[i][j].getI(),
										tabuleiro[i][j].getJ(),
										tabuleiro[i][j].getCor());
								destino = new Botao(tabuleiro[k][l].getI(),
										tabuleiro[k][l].getJ(),
										tabuleiro[k][l].getCor());
								destino.setBackground(tabuleiro[k][l]
										.getBackground());
								actions.add(new Action(origem, destino, clonaTabuleiro(tabuleiro)));
							}
						}
					}
				}
			}
		}

		return actions;
	}

	public void printActions(ArrayList<Action> actions) {
		System.err.println("Actions: ");
		for (Action action : actions) {
			System.err.println("Origem: I = " + action.getOrigem().getI()
					+ " J = " + action.getOrigem().getJ() + " Destino: I = "
					+ action.getDestino().getI() + " J = "
					+ action.getDestino().getJ() + " Cor: "
					+ action.getDestino().getBackground());
		}
	}

	public Botao procurarPecaEscolhidaParaComer(Action action) {
		Botao[][] tabuleiro = clonaTabuleiro(action.getTabuleiro());
		// Posição acima à direita
		if (topRight(action.getDestino())) {
			if (tabuleiro[action.getDestino().getI() - 1][action.getDestino().getJ() + 1].isEmRisco()) {
				System.err.println("topRight em risco");
				return tabuleiro[action.getDestino().getI() - 1][action.getDestino().getJ() + 1];
			}
		}

		// Posição abaixo à direita
		if (downRight(action.getDestino())) {
			if (tabuleiro[action.getDestino().getI() + 1][action.getDestino().getJ() + 1].isEmRisco()) {
				System.err.println("downRight em risco");
				return tabuleiro[action.getDestino().getI() + 1][action.getDestino().getJ() + 1];
			}
		}

		// Posição acima à esquerda
		if (topLeft(action.getDestino())) {
			if (tabuleiro[action.getDestino().getI() - 1][action.getDestino().getJ() - 1].isEmRisco()) {
				System.err.println("topLeft em risco");
				return tabuleiro[action.getDestino().getI() - 1][action.getDestino().getJ() - 1];
			}
		}

		// Posição abaixo à esquerda
		if (downLeft(action.getDestino())) {
			if (tabuleiro[action.getDestino().getI() + 1][action.getDestino().getJ() - 1].isEmRisco()) {
				System.err.println("downLeft em risco");
				return tabuleiro[action.getDestino().getI() + 1][action.getDestino().getJ() - 1];
			}
		}

		return null;
	}

	public void comerPeca(Botao btnLooser, Botao btnWinner, Botao btnPosicao,
			Botao[][] tabuleiro) {
		//System.err.println("tabuleiro: "+tabuleiro == null);
		System.err.println("btnLooser: ");
		System.err.println(btnLooser == null);		
		//System.err.println("btnLooser.getI(): "+btnLooser.getI() == null);
		//System.err.println("btnLooser.getJ(): "+btnLooser.getJ() == null);
		

		tabuleiro[btnLooser.getI()][btnLooser.getJ()].setIcon(null);

		// Marca a peça que será comida como livre (ocupada = false);
		tabuleiro[btnLooser.getI()][btnLooser.getJ()].setOcupado(false);

		tabuleiro[btnLooser.getI()][btnLooser.getJ()].setEmRisco(false);

		tabuleiro[btnLooser.getI()][btnLooser.getJ()].setCor(Cor.VAZIO);

		// Move a peça para a posição correta
		moverPeca(btnWinner, btnPosicao, tabuleiro);

		// Volta a cor do botão ao normal
		tabuleiro[btnPosicao.getI()][btnPosicao.getJ()]
				.setBackground(Color.LIGHT_GRAY);
		
	}

	public void moverPeca(Botao btnOrigem, Botao btnDestino, Botao[][] tabuleiro) {
		tabuleiro[btnOrigem.getI()][btnOrigem.getJ()].setIcon(null);

		// Marca a peça de origem como livre. (ocupado = false)
		tabuleiro[btnOrigem.getI()][btnOrigem.getJ()].setOcupado(false);

		// Marca a peça de destino como ocupada. (ocupado = true)
		tabuleiro[btnDestino.getI()][btnDestino.getJ()].setOcupado(true);

		if (btnOrigem.getCor() == Cor.AZUL) {
			tabuleiro[btnDestino.getI()][btnDestino.getJ()].setIcon(icon1);
			tabuleiro[btnDestino.getI()][btnDestino.getJ()].setCor(Cor.AZUL);
		} else if (btnOrigem.getCor() == Cor.VERMELHA) {
			tabuleiro[btnDestino.getI()][btnDestino.getJ()].setIcon(icon2);
			tabuleiro[btnDestino.getI()][btnDestino.getJ()]
					.setCor(Cor.VERMELHA);
		}

		tabuleiro[btnOrigem.getI()][btnOrigem.getJ()].setCor(Cor.VAZIO);
	}

	// Esse metódo pinta de vermelho as posições válidas em que uma peça pode se
	// mover.
	// A primeira condição serve para evitar um NullPointerException
	// Posições à direita: sempre incrementar o J
	// Posições à esquerda: sempre decrementar o J
	public void mostrarOpcoes(Botao btn, Botao[][] tabuleiro) {
		repintarTabuleiro(tabuleiro);

		// Posição acima à direita
		if (topRight(btn)) {
			if (!tabuleiro[btn.getI() - 1][btn.getJ() + 1].isOcupado()) {
				tabuleiro[btn.getI() - 1][btn.getJ() + 1]
						.setBackground(Color.RED);
			}
			if (tabuleiro[btn.getI() - 1][btn.getJ() + 1].getCor() != Cor.VAZIO) {
				if (btn.getCor() != tabuleiro[btn.getI() - 1][btn.getJ() + 1]
						.getCor()) {
					mostrarOpcoesParaComer(tabuleiro[btn.getI() - 1][btn.getJ() + 1], btn,
							tabuleiro);
				}
			}
		}

		// Posição abaixo à direita
		if (downRight(btn)) {
			if (!tabuleiro[btn.getI() + 1][btn.getJ() + 1].isOcupado()) {
				tabuleiro[btn.getI() + 1][btn.getJ() + 1]
						.setBackground(Color.RED);
			}
			if (tabuleiro[btn.getI() + 1][btn.getJ() + 1].getCor() != Cor.VAZIO) {
				if (btn.getCor() != tabuleiro[btn.getI() + 1][btn.getJ() + 1]
						.getCor()) {					
					mostrarOpcoesParaComer(tabuleiro[btn.getI() + 1][btn.getJ() + 1], btn,
							tabuleiro);
				}
			}
		}

		// Posição acima à esquerda
		if (topLeft(btn)) {
			if (!tabuleiro[btn.getI() - 1][btn.getJ() - 1].isOcupado()) {
				tabuleiro[btn.getI() - 1][btn.getJ() - 1]
						.setBackground(Color.RED);
			}
			if (tabuleiro[btn.getI() - 1][btn.getJ() - 1].getCor() != Cor.VAZIO) {
				if (btn.getCor() != tabuleiro[btn.getI() - 1][btn.getJ() - 1]
						.getCor()) {
					mostrarOpcoesParaComer(tabuleiro[btn.getI() - 1][btn.getJ() - 1], btn,
							tabuleiro);
				}
			}
		}

		// Posição abaixo à esquerda
		if (downLeft(btn)) {
			if (!tabuleiro[btn.getI() + 1][btn.getJ() - 1].isOcupado()) {
				tabuleiro[btn.getI() + 1][btn.getJ() - 1]
						.setBackground(Color.RED);
			}
			if (tabuleiro[btn.getI() + 1][btn.getJ() - 1].getCor() != Cor.VAZIO) {
				if (btn.getCor() != tabuleiro[btn.getI() + 1][btn.getJ() - 1]
						.getCor()) {
					mostrarOpcoesParaComer(tabuleiro[btn.getI() + 1][btn.getJ() - 1], btn,
							tabuleiro);
				}
			}
		}
	}

	public void mostrarOpcoesParaComer(Botao btnLooser, Botao btnWinner,
			Botao[][] tabuleiro) {
		// Posição acima à direita
		if (topRight(btnLooser)) {
			if (!tabuleiro[btnLooser.getI() - 1][btnLooser.getJ() + 1]
					.isOcupado()) {
				if (btnWinner.getI() != tabuleiro[btnLooser.getI() - 1][btnLooser
						.getJ() + 1].getI()
						&& btnWinner.getJ() != tabuleiro[btnLooser.getI() - 1][btnLooser
								.getJ() + 1].getJ()) {
					tabuleiro[btnLooser.getI() - 1][btnLooser.getJ() + 1]
							.setBackground(Color.GREEN);
					btnLooser.setEmRisco(true);
				}
			}
		}

		// Posição abaixo à direita
		if (downRight(btnLooser)) {
			if (!tabuleiro[btnLooser.getI() + 1][btnLooser.getJ() + 1]
					.isOcupado()) {
				if (btnWinner.getI() != tabuleiro[btnLooser.getI() + 1][btnLooser
						.getJ() + 1].getI()
						&& btnWinner.getJ() != tabuleiro[btnLooser.getI() + 1][btnLooser
								.getJ() + 1].getJ()) {
					tabuleiro[btnLooser.getI() + 1][btnLooser.getJ() + 1]
							.setBackground(Color.GREEN);
					btnLooser.setEmRisco(true);
				}
			}
		}

		// Posição acima à esquerda
		if (topLeft(btnLooser)) {
			if (!tabuleiro[btnLooser.getI() - 1][btnLooser.getJ() - 1]
					.isOcupado()) {
				if (btnWinner.getI() != tabuleiro[btnLooser.getI() - 1][btnLooser
						.getJ() - 1].getI()
						&& btnWinner.getJ() != tabuleiro[btnLooser.getI() - 1][btnLooser
								.getJ() - 1].getJ()) {
					tabuleiro[btnLooser.getI() - 1][btnLooser.getJ() - 1]
							.setBackground(Color.GREEN);
					btnLooser.setEmRisco(true);
				}
			}
		}

		// Posição abaixo à esquerda
		if (downLeft(btnLooser)) {
			if (!tabuleiro[btnLooser.getI() + 1][btnLooser.getJ() - 1]
					.isOcupado()) {
				if (btnWinner.getI() != tabuleiro[btnLooser.getI() + 1][btnLooser
						.getJ() - 1].getI()
						&& btnWinner.getJ() != tabuleiro[btnLooser.getI() - 1][btnLooser
								.getJ() - 1].getJ()) {
					tabuleiro[btnLooser.getI() + 1][btnLooser.getJ() - 1]
							.setBackground(Color.GREEN);
					btnLooser.setEmRisco(true);
				}
			}
		}
	}

	public void repintarTabuleiro(Botao[][] tabuleiro) {

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {

				// Inicialmente pinta todos os botões de cinza
				tabuleiro[i][j].setBackground(Color.LIGHT_GRAY);
				// Pinta o tabuleiro alternadamente de branco (ficando a grid
				// interpolada entre branco e cinza)
				if (i % 2 == 0) {
					if (j % 2 == 0)
						tabuleiro[i][j].setBackground(Color.WHITE);
				} else {
					if (j % 2 != 0) {
						tabuleiro[i][j].setBackground(Color.WHITE);
					}
				}
			}
		}
	}

	public void imprimiTabuleiro(Botao[][] tabuleiro) {
		System.err.println("Tabuleiro: ");
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				System.err.print(tabuleiro[i][j].getCor() + " ");
			}
			System.err.println();
		}

	}

	public boolean topRight(Botao btn) {
		return (btn.getI() - 1 >= 0 && btn.getI() - 1 < 8)
				&& (btn.getJ() + 1 >= 0 && btn.getJ() + 1 < 8);
	}

	public boolean topLeft(Botao btn) {
		return (btn.getI() - 1 >= 0 && btn.getI() - 1 < 8)
				&& (btn.getJ() - 1 >= 0 && btn.getJ() - 1 < 8);
	}

	public boolean downRight(Botao btn) {
		return (btn.getI() + 1 >= 0 && btn.getI() + 1 < 8)
				&& (btn.getJ() + 1 >= 0 && btn.getJ() + 1 < 8);
	}

	public boolean downLeft(Botao btn) {
		return (btn.getI() + 1 >= 0 && btn.getI() + 1 < 8)
				&& (btn.getJ() - 1 >= 0 && btn.getJ() - 1 < 8);
	}
	
}
