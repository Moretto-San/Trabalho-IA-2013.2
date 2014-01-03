
package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AboutBox extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel            labelAutores;
	private JLabel            lblNomeAutores;
	private JLabel            lblDenysonHenriqueDos;
	private JLabel            lblLeonardoBarbieriBedendo;
	private JLabel            lblMarceloMartelliAymori;

	public AboutBox() {
		setResizable( false );
		getContentPane().setForeground( Color.DARK_GRAY );
		getContentPane().setBackground( Color.DARK_GRAY );
		setForeground( Color.DARK_GRAY );
		setSize( 278, 293 );
		setTitle( "Sobre..." );
		setLocationRelativeTo( null );
		getContentPane().setLayout( null );
		getContentPane().add( getLblMarceloMartelliAymori() );
		getContentPane().add( getLblDenysonHenriqueDos() );
		getContentPane().add( getLblLeonardoBarbieriBedendo() );
		getContentPane().add( getLblNomeAutores() );

		labelAutores = new JLabel( "" );
		labelAutores.setBounds( 2, 5, 264, 265 );
		labelAutores.setHorizontalAlignment( SwingConstants.CENTER );
		labelAutores.setBackground( Color.DARK_GRAY );
		labelAutores.setForeground( Color.DARK_GRAY );
		labelAutores.setIcon( new ImageIcon( AboutBox.class.getResource( "/img/04-damasMedioTransp.png" ) ) );
		getContentPane().add( labelAutores );

		this.addKeyListener( new KeyAdapter() {
			@ Override
			public void keyPressed( KeyEvent e ) {
				if( e.getKeyChar() == 27 ) {
					dispose();
				}
			}
		} );
	}

	private JLabel getLblNomeAutores() {
		if( lblNomeAutores == null ) {
			lblNomeAutores = new JLabel( "Autores:" );
			lblNomeAutores.setFont( new Font( "Tahoma", Font.BOLD, 18 ) );
			lblNomeAutores.setBounds( 2, 24, 264, 33 );
			lblNomeAutores.setHorizontalAlignment( SwingConstants.CENTER );
			lblNomeAutores.setForeground( Color.ORANGE );
			lblNomeAutores.setBackground( Color.ORANGE );
		}
		return lblNomeAutores;
	}

	private JLabel getLblDenysonHenriqueDos() {
		if( lblDenysonHenriqueDos == null ) {
			lblDenysonHenriqueDos = new JLabel( "Denyson Henrique dos Reis Bott" );
			lblDenysonHenriqueDos.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
			lblDenysonHenriqueDos.setHorizontalAlignment( SwingConstants.CENTER );
			lblDenysonHenriqueDos.setForeground( Color.ORANGE );
			lblDenysonHenriqueDos.setBackground( Color.WHITE );
			lblDenysonHenriqueDos.setBounds( 2, 123, 264, 33 );
		}
		return lblDenysonHenriqueDos;
	}

	private JLabel getLblLeonardoBarbieriBedendo() {
		if( lblLeonardoBarbieriBedendo == null ) {
			lblLeonardoBarbieriBedendo = new JLabel( "Leonardo Barbieri Bedendo" );
			lblLeonardoBarbieriBedendo.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
			lblLeonardoBarbieriBedendo.setHorizontalAlignment( SwingConstants.CENTER );
			lblLeonardoBarbieriBedendo.setForeground( Color.ORANGE );
			lblLeonardoBarbieriBedendo.setBackground( Color.WHITE );
			lblLeonardoBarbieriBedendo.setBounds( 2, 157, 264, 33 );
		}
		return lblLeonardoBarbieriBedendo;
	}

	private JLabel getLblMarceloMartelliAymori() {
		if( lblMarceloMartelliAymori == null ) {
			lblMarceloMartelliAymori = new JLabel( "Marcelo Martelli Aymori" );
			lblMarceloMartelliAymori.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
			lblMarceloMartelliAymori.setHorizontalAlignment( SwingConstants.CENTER );
			lblMarceloMartelliAymori.setForeground( Color.ORANGE );
			lblMarceloMartelliAymori.setBackground( Color.WHITE );
			lblMarceloMartelliAymori.setBounds( 2, 190, 264, 33 );
		}
		return lblMarceloMartelliAymori;
	}
}
