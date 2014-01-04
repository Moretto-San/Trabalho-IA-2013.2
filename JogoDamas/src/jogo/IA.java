package jogo;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class IA {
	private Cor corPecaIa = Cor.VERMELHA;
	private ArrayList<Botao> botoesASeremComidos = new ArrayList<Botao>();
	private int index = -1;
	private ImageIcon icon1 = new ImageIcon(
			Tabuleiro.class.getResource("/img/circle-blue.png")); // Peça Azul
	private ImageIcon icon2 = new ImageIcon(
			Tabuleiro.class.getResource("/img/circle-red.png")); // Peça
																	// Vermelha

	public Action ALPHABETASEARCH(Botao[][] tabuleiro) {
		ArrayList<Action> actions = getActions(tabuleiro);
		for (Action action : actions) {

		}
		return null;
	}

	public int MAXVALUE(Botao[][] tabuleiro, int alfa, int beta) {
		ArrayList<Action> actions = getActions(tabuleiro);
		int maior = Integer.MAX_VALUE;
		int aux = 0;
		if (verificarPorVencedor(tabuleiro) == 1) {
			return 1;
		}
		for (Action action : actions) {
			if (MINVALUE(result(action, tabuleiro), alfa, beta) >= 1) {

			}
		}
		return 0;
	}

	public int MINVALUE(Botao[][] tabuleiro, int alfa, int beta) {
		ArrayList<Action> actions = getActions(tabuleiro);
		int menor = Integer.MIN_VALUE;
		int aux = 0;
		if (verificarPorVencedor(tabuleiro) == -1) {
			return -1;
		}
		for (Action action : actions) {

		}
		return 0;
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
			return +1;
		} else if (contadorPecasVermelhas == 0) {
			return -1;
		}
		return 0;
	}

	public Botao[][] result(Action acao, Botao[][] tabuleiroIn) {
		Botao[][] tabuleiro = tabuleiroIn.clone();
		if (acao.getOrigem().getBackground() == Color.GREEN) {
			comerPeca(
					procurarPecaEscolhidaParaComer(acao.getDestino(), tabuleiro),
					acao.getOrigem(), acao.getDestino(), tabuleiro);
		}
		if (acao.getOrigem().getBackground() == Color.RED) {
			moverPeca(acao.getOrigem(), acao.getDestino(), tabuleiro);
		}
		return tabuleiro;
	}

	public ArrayList<Action> getActions(Botao[][] tabuleiro) {
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				if (tabuleiro[i][j].getCor().equals(corPecaIa)) {
					mostrarOpcoes(tabuleiro[i][j], tabuleiro);
					for (int k = 0; k < tabuleiro.length; k++) {
						for (int l = 0; l < tabuleiro.length; l++) {
							if (tabuleiro[k][l].getBackground() == Color.GREEN
									|| tabuleiro[k][l].getBackground() == Color.RED) {
								actions.add(new Action(tabuleiro[i][j],
										tabuleiro[k][l]));
							}
						}
					}
				}
			}
		}
		return actions;
	}

	public Botao procurarPecaEscolhidaParaComer(Botao btn, Botao[][] tabuleiro) {

		// Posição acima à direita
		if (topRight(btn)) {
			if (tabuleiro[btn.getI() - 1][btn.getJ() + 1].isEmRisco()) {
				return tabuleiro[btn.getI() - 1][btn.getJ() + 1];
			}
		}

		// Posição abaixo à direita
		if (downRight(btn)) {
			if (tabuleiro[btn.getI() + 1][btn.getJ() + 1].isEmRisco()) {
				return tabuleiro[btn.getI() + 1][btn.getJ() + 1];
			}
		}

		// Posição acima à esquerda
		if (topLeft(btn)) {
			if (tabuleiro[btn.getI() - 1][btn.getJ() - 1].isEmRisco()) {
				return tabuleiro[btn.getI() - 1][btn.getJ() - 1];
			}
		}

		// Posição abaixo à esquerda
		if (downLeft(btn)) {
			if (tabuleiro[btn.getI() + 1][btn.getJ() - 1].isEmRisco()) {
				return tabuleiro[btn.getI() + 1][btn.getJ() - 1];
			}
		}

		return null;
	}

	public void comerPeca(Botao btnLooser, Botao btnWinner, Botao btnPosicao,
			Botao[][] tabuleiro) {
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
					botoesASeremComidos.add(tabuleiro[btn.getI() - 1][btn
							.getJ() + 1]);
					index++;
					mostrarOpcoesParaComer(botoesASeremComidos.get(index), btn,
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
					botoesASeremComidos.add(tabuleiro[btn.getI() + 1][btn
							.getJ() + 1]);
					index++;
					mostrarOpcoesParaComer(botoesASeremComidos.get(index), btn,
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
					botoesASeremComidos.add(tabuleiro[btn.getI() - 1][btn
							.getJ() - 1]);
					index++;
					mostrarOpcoesParaComer(botoesASeremComidos.get(index), btn,
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
					botoesASeremComidos.add(tabuleiro[btn.getI() + 1][btn
							.getJ() - 1]);
					index++;
					mostrarOpcoesParaComer(botoesASeremComidos.get(index), btn,
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
					if (j % 2 != 0)
						tabuleiro[i][j].setBackground(Color.WHITE);
				}
			}
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
