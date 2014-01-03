
package jogo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel            panelMenu;
	private Tabuleiro         panelTabuleiro;
	private JPanel            panelJogadores;
	private JMenuBar          menuBar;
	private JMenu             menuHelp;
	private JMenu             menuJogo;
	private JMenuItem         menuItemSobre;
	private JMenuItem         menuItemReiniciarJogo;
	private String            player1          = "";
	private String            player2          = "";
	private JLabel            labelPlayer1;
	private JLabel            labelPlayer2;
	private JLabel            labelX;


	
	//Servidor
	public Principal( final String jogador1, String jogador2){
		
		initialize(jogador1, jogador2);
		
		//Carrega o tabuleiro do jogo
		panelTabuleiro = new Tabuleiro();
		getContentPane().add( panelTabuleiro, BorderLayout.CENTER );
	}

	private void carregaJogador( String jogador1, String jogador2 ) {
		labelPlayer1 = new JLabel( jogador1 );
		labelX = new JLabel( " x " );
		labelPlayer2 = new JLabel( jogador2 );
		
		if( jogador1.isEmpty() ) {
			labelPlayer1.setText( "Player 1" );
		}

		if( jogador2.isEmpty() ) {
			labelPlayer2.setText( "Player 2" );
		}
		labelPlayer1.setForeground( Color.RED );
		labelPlayer2.setForeground( Color.BLUE );
	}
	
	private void initialize(String jogador1, String jogador2) {
		
		setResizable( false );
		setBounds( 341, 10, 600, 600 );
		setTitle( "Jogo de Damas" );
		getContentPane().setLayout( new BorderLayout() );
		panelMenu = new JPanel( new FlowLayout() );
		panelJogadores = new JPanel( new FlowLayout() );
		
		menuBar = new JMenuBar();
		menuHelp = new JMenu( "Help" );
		menuHelp.setMnemonic( KeyEvent.VK_H );
		menuItemSobre = new JMenuItem( "Sobre..." );
		menuHelp.add( menuItemSobre );
		
		carregaJogador( jogador1, jogador2 );

		menuJogo = new JMenu( "Jogo" );
		menuJogo.setMnemonic( KeyEvent.VK_J );
		menuItemReiniciarJogo = new JMenuItem( "Reiniciar" );
		menuJogo.add( menuItemReiniciarJogo );

		menuBar.add( menuJogo );
		menuBar.add( menuHelp );

		panelJogadores.add( labelPlayer1 );
		panelJogadores.add( labelX );
		panelJogadores.add( labelPlayer2 );
		panelMenu.add( menuBar );
		getContentPane().add( panelMenu, BorderLayout.NORTH );
		getContentPane().add( panelJogadores, BorderLayout.SOUTH );

		menuItemSobre.addActionListener( new ActionListener() {
			@ Override
			public void actionPerformed( ActionEvent e ) {
				AboutBox ab = new AboutBox();
				ab.setVisible( true );
			}
		} );

		menuItemReiniciarJogo.addActionListener( new ActionListener() {

			@ Override
			public void actionPerformed( ActionEvent e ) {
				//Provisório
				reiniciar();
			}
		} );		
	}
	
	//reiniciando o jogo
	private void reiniciar() {
		dispose();//temporário
	}
	
	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1( String player1 ) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2( String player2 ) {
		this.player2 = player2;
	}
	
}

enum Cor {
	VERMELHA, AZUL, VAZIO;
}