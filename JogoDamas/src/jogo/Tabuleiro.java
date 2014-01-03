package jogo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel {

	private static final long serialVersionUID = 1L;
	private Botao tabuleiro[][] = new Botao[8][8];
	private GridLayout gridLayout = new GridLayout(8, 8, 2, 2);
	private ImageIcon icon1 = new ImageIcon(
			Tabuleiro.class.getResource("/img/circle-blue.png")); // Peça Azul
	private ImageIcon icon2 = new ImageIcon(
			Tabuleiro.class.getResource("/img/circle-red.png")); // Peça
																	// Vermelha
	private Botao btnOrigem;
	private ArrayList<Botao> botoesASeremComidos = new ArrayList<Botao>();
	private int index = -1;
	private boolean suaVezDeJogar;
	private Cor minhaPeca;
	private Cor corPecaIa;

	// Cliente
	public Tabuleiro() {
		setLayout(gridLayout);
		// Carrega o tabuleiro com as peças em posição
		carregarTabuleiro();
		carregarPecas();
		// receberMatriz();
		suaVezDeJogar = true;
		minhaPeca = Cor.AZUL;
		corPecaIa = Cor.VERMELHA;
	}

	private void carregarTabuleiro() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				tabuleiro[i][j] = new Botao(i, j);

				// Inicialmente pinta todos os botões de cinza
				tabuleiro[i][j].setBackground(Color.LIGHT_GRAY);
				// Pinta o tabuleiro alternadamente de branco (ficando a grid
				// interpolada entre branco e cinza)
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						tabuleiro[i][j].setBackground(Color.WHITE);
					}
				} else {
					if (j % 2 != 0) {
						tabuleiro[i][j].setBackground(Color.WHITE);
					}
				}
				add(tabuleiro[i][j]);
				tabuleiro[i][j].addActionListener(new ActionListener() {
					private Botao btn;

					@Override
					public void actionPerformed(ActionEvent e) {
						btn = (Botao) e.getSource();

						HandleButtonEvent(btn);
					}
				});
			}
		}
	}

	private void carregarTabuleiro(MatrizTabuleiro matriz) {
		removeAll();
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				tabuleiro[i][j] = matriz.getTabuleiro()[i][j];
				add(tabuleiro[i][j]);
				tabuleiro[i][j].addActionListener(new ActionListener() {
					private Botao btn;

					@Override
					public void actionPerformed(ActionEvent e) {
						btn = (Botao) e.getSource();

						HandleButtonEvent(btn);
					}
				});
			}
		}
	}

	private void HandleButtonEvent(Botao btn) {
		if (!suaVezDeJogar) {
			JOptionPane.showMessageDialog(null, "Não é a sua vez de jogar!",
					"Atenção!", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		boolean enviarMatriz = false;
		if (btn.getCor() == Cor.VERMELHA || btn.getCor() == Cor.AZUL) {
			if (btn.getCor() == minhaPeca) {
				mostrarOpcoes(btn);
				btnOrigem = btn;
			}
		}

		if (btn.getBackground() == Color.RED) {
			moverPeca(btnOrigem, btn);
			enviarMatriz = true;
		}

		else if (btn.getBackground() == Color.GREEN) {
			comerPeca(procurarPecaEscolhidaParaComer(btn), btnOrigem, btn);
			enviarMatriz = true;
		}

		if (enviarMatriz) {

			// enviarMatriz(tabuleiro);
			// receberMatriz();
			suaVezDeJogar = false;
			vezIA();
			repintarTabuleiro();
		}

		verificarPorVencedor();
	}

	public void carregarPecas() {
		// Carregando as peças azuis
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (tabuleiro[i][j].getBackground() == Color.LIGHT_GRAY) {
					tabuleiro[i][j].setIcon(icon1);
					tabuleiro[i][j].setCor(Cor.AZUL);
					tabuleiro[i][j].setOcupado(true);
				}
			}
		}

		// Carregando as peças vermelhas
		for (int i = 5; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (tabuleiro[i][j].getBackground() == Color.LIGHT_GRAY) {
					tabuleiro[i][j].setIcon(icon2);
					tabuleiro[i][j].setCor(Cor.VERMELHA);
					tabuleiro[i][j].setOcupado(true);
				}
			}
		}

		// Atribui a todos os botões que não possuem ícone a cor VAZIO
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (tabuleiro[i][j].getIcon() == null) {
					tabuleiro[i][j].setCor(Cor.VAZIO);
				}
			}
		}
	}

	public void repintarTabuleiro() {

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

	// Esse metódo pinta de vermelho as posições válidas em que uma peça pode se
	// mover.
	// A primeira condição serve para evitar um NullPointerException
	// Posições à direita: sempre incrementar o J
	// Posições à esquerda: sempre decrementar o J
	public void mostrarOpcoes(Botao btn) {
		repintarTabuleiro();

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
					mostrarOpcoesParaComer(botoesASeremComidos.get(index), btn);
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
					mostrarOpcoesParaComer(botoesASeremComidos.get(index), btn);
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
					mostrarOpcoesParaComer(botoesASeremComidos.get(index), btn);
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
					mostrarOpcoesParaComer(botoesASeremComidos.get(index), btn);
				}
			}
		}
	}

	// Esse método pinta de vermelho as opções em que a peça poderá comer uma
	// peça adversária
	public void mostrarOpcoesParaComer(Botao btnLooser, Botao btnWinner) {
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

	// Esse método excluirá o botão que for passado por parâmetro
	public void comerPeca(Botao btnLooser, Botao btnWinner, Botao btnPosicao) {
		tabuleiro[btnLooser.getI()][btnLooser.getJ()].setIcon(null);

		// Marca a peça que será comida como livre (ocupada = false);
		tabuleiro[btnLooser.getI()][btnLooser.getJ()].setOcupado(false);

		tabuleiro[btnLooser.getI()][btnLooser.getJ()].setEmRisco(false);

		tabuleiro[btnLooser.getI()][btnLooser.getJ()].setCor(Cor.VAZIO);

		// Move a peça para a posição correta
		moverPeca(btnWinner, btnPosicao);

		// Volta a cor do botão ao normal
		tabuleiro[btnPosicao.getI()][btnPosicao.getJ()]
				.setBackground(Color.LIGHT_GRAY);
	}

	// Esse método serve para encontrar a peça do adversário que o jogador
	// escolheu para comer.
	// Para isso ele sempre deve receber como parâmetro o botão com fundo verde
	// (ou seja, aquele que o jogador escolheu para
	// comer a peça adversária)
	public Botao procurarPecaEscolhidaParaComer(Botao btn) {

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

	// Recebe como parâmetro o botão de origem e destino (que receberá a peça)
	public void moverPeca(Botao btnOrigem, Botao btnDestino) {
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

	public void verificarPorVencedor() {
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
			JOptionPane.showMessageDialog(null, "Vermelho venceu!");
		} else if (contadorPecasVermelhas == 0) {
			JOptionPane.showMessageDialog(null, "Azuls venceu!");
		}
	}

	public void vezIA() {
		ALPHABETASEARCH(tabuleiro);
	}

	public Action ALPHABETASEARCH(Botao[][] tabuleiro) {
		ArrayList<Action> actions = getActions(tabuleiro);
		for (Action action : actions) {
			
		}
		return null; 
	}

	public int MAXVALUE(Botao[][] tabuleiro, int alfa, int beta) {
		ArrayList<Action> actions = getActions(tabuleiro);
		int maior = Integer.MAX_VALUE;
		return 0;
	}

	public int MINVALUE(Botao[][] tabuleiro, int alfa, int beta) {
		ArrayList<Action> actions = getActions(tabuleiro);
		int menor = Integer.MIN_VALUE;
		for (Action action : actions) {
			
		}
		return 0;
	}

	public ArrayList<Botao[][]> getEstados(Botao[][] tabuleiro) {
	return null;
	}
	
	public ArrayList<Action> getActions(Botao[][] tabuleiro) {
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				if (tabuleiro[i][j].getCor().equals(corPecaIa)) {
					mostrarOpcoes(tabuleiro[i][j]);
					for (int k = 0; k < tabuleiro.length; k++) {
						for (int l = 0; l < tabuleiro.length; l++) {
							if (tabuleiro[k][l].getBackground() == Color.GREEN) {
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

	public class Action {
		Botao origem;
		Botao destino;

		public Action(Botao origem, Botao destino) {
			this.origem = origem;
			this.destino = destino;
		}
	}

}