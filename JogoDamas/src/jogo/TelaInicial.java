package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frmDamas;
	private JPanel panel;
	private JLabel lblJogar;
	private JLabel lblNewLabel;
	private JLabel lblSair;
	private JLabel lblDamas;
	private JLabel lblPlayer;
	private JTextField txtPlayer;
	private JLabel lblIniciarJogo;
	private boolean isServidor = false;

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			TelaInicial window = new TelaInicial();
			window.frmDamas.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TelaInicial() {
		initialize();
	}

	private void initialize() {
		frmDamas = new JFrame();
		frmDamas.setIconImage(Toolkit.getDefaultToolkit().getImage(
				TelaInicial.class.getResource("/img/04-damas.png")));
		frmDamas.setResizable(false);
		frmDamas.getContentPane().setBackground(Color.BLACK);
		frmDamas.setBackground(Color.BLACK);
		frmDamas.setTitle("Jogo de Damas");
		frmDamas.setBounds(341, 50, 537, 545);
		frmDamas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDamas.getContentPane().add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			panel.setBackground(Color.BLACK);
			panel.setForeground(Color.BLACK);
			panel.setLayout(null);
			panel.add(getLblIniciarJogo());
			panel.add(getLblPlayer());
			panel.add(getTxtPlayer());
			panel.add(getLblJogar());
			panel.add(getLblSair());
			panel.add(getLblDamas());
			panel.add(getLblNewLabel());
		}
		return panel;
	}

	private JLabel getLblJogar() {
		if (lblJogar == null) {
			lblJogar = new JLabel("Jogar");
			lblJogar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblJogar.setForeground(Color.ORANGE);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					lblJogar.setForeground(Color.WHITE);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					lblPlayer.setVisible(true);
					txtPlayer.setText("");
					txtPlayer.setVisible(true);
					lblIniciarJogo.setVisible(false);
					lblDamas.setForeground(Color.ORANGE);
					isServidor = false;
				}
			});
			lblJogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblJogar.setDisplayedMnemonic('U');
			lblJogar.setBackground(Color.DARK_GRAY);
			lblJogar.setFont(new Font("Calisto MT", Font.BOLD, 24));
			lblJogar.setForeground(Color.WHITE);
			lblJogar.setBounds(79, 308, 155, 32);
		}
		return lblJogar;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			lblNewLabel.setIcon(new ImageIcon(TelaInicial.class
					.getResource("/img/04-damas.png")));
			lblNewLabel.setCursor(Cursor
					.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
			lblNewLabel.setBounds(0, 0, 528, 530);
		}
		return lblNewLabel;
	}

	private JLabel getLblSair() {
		if (lblSair == null) {
			lblSair = new JLabel("Sair");
			lblSair.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblSair.setForeground(Color.ORANGE);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					lblSair.setForeground(Color.WHITE);
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					System.exit(0);
				}
			});
			lblSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblSair.setForeground(Color.WHITE);
			lblSair.setFont(new Font("Calisto MT", Font.BOLD, 24));
			lblSair.setBackground(Color.RED);
			lblSair.setBounds(79, 393, 155, 32);
		}
		return lblSair;
	}

	private JLabel getLblDamas() {
		if (lblDamas == null) {
			lblDamas = new JLabel("Damas");
			lblDamas.setForeground(Color.WHITE);
			lblDamas.setFont(new Font("Dialog", Font.BOLD, 44));
			lblDamas.setBounds(330, 22, 144, 57);
		}
		return lblDamas;
	}

	private JLabel getLblPlayer() {
		if (lblPlayer == null) {
			lblPlayer = new JLabel("Jogador");
			lblPlayer.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
			lblPlayer.setVisible(false);
			lblPlayer.setForeground(SystemColor.activeCaption);
			lblPlayer.setBounds(282, 91, 155, 26);
		}
		return lblPlayer;
	}

	private JTextField getTxtPlayer() {
		if (txtPlayer == null) {
			txtPlayer = new JTextField();
			txtPlayer.setBackground(Color.LIGHT_GRAY);
			txtPlayer.setFont(new Font("SansSerif", Font.BOLD, 14));
			txtPlayer.setVisible(false);
			txtPlayer.setBounds(282, 120, 155, 28);
			txtPlayer.setColumns(15);
			txtPlayer.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if ((txtPlayer.getText() == " ")) {
						lblIniciarJogo.setVisible(false);
					} else {
						lblIniciarJogo.setVisible(true);
					}
				}
			});
		}
		return txtPlayer;
	}

	private JLabel getLblIniciarJogo() {
		if (lblIniciarJogo == null) {
			lblIniciarJogo = new JLabel("");
			lblIniciarJogo.setToolTipText("Clique para iniciar o jogo!!!");
			lblIniciarJogo.setIcon(new ImageIcon(TelaInicial.class
					.getResource("/img/Next72.png")));
			lblIniciarJogo.setVisible(false);
			lblIniciarJogo.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblIniciarJogo.setFont(new Font("Segoe UI",
					Font.BOLD | Font.ITALIC, 28));
			lblIniciarJogo.setForeground(Color.RED);
			lblIniciarJogo.setBounds(402, 424, 72, 72);
			lblIniciarJogo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (isServidor) {
						iniciaPrincipal(txtPlayer.getText());
					} else {
						iniciaPrincipal(txtPlayer.getText());
					}
				}
			});
		}
		return lblIniciarJogo;
	}

	private void iniciaPrincipal(String jogador) {
		Principal principal = new Principal(jogador);
		principal.setVisible(true);
		this.setVisible(false);
	}

}
