
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
	private JLabel            lblMorett;
	private JLabel            lblRai;
	private JLabel            lblGisele;

	public AboutBox() {
		setResizable( false );
		getContentPane().setForeground( Color.DARK_GRAY );
		getContentPane().setBackground( Color.DARK_GRAY );
		setForeground( Color.DARK_GRAY );
		setSize( 278, 293 );
		setTitle( "Sobre..." );
		setLocationRelativeTo( null );
		getContentPane().setLayout( null );
		getContentPane().add( getLblGisele() );
		getContentPane().add( getLblMorett() );
		getContentPane().add( getLblRai() );
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

	private JLabel getLblMorett() {
		if( lblMorett == null ) {
			lblMorett = new JLabel( "Rafael Braga Morett" );
			lblMorett.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
			lblMorett.setHorizontalAlignment( SwingConstants.CENTER );
			lblMorett.setForeground( Color.ORANGE );
			lblMorett.setBackground( Color.WHITE );
			lblMorett.setBounds( 2, 123, 264, 33 );
		}
		return lblMorett;
	}

	private JLabel getLblRai() {
		if( lblRai == null ) {
			lblRai = new JLabel( "Rai Gomes" );
			lblRai.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
			lblRai.setHorizontalAlignment( SwingConstants.CENTER );
			lblRai.setForeground( Color.ORANGE );
			lblRai.setBackground( Color.WHITE );
			lblRai.setBounds( 2, 157, 264, 33 );
		}
		return lblRai;
	}

	private JLabel getLblGisele() {
		if( lblGisele == null ) {
			lblGisele = new JLabel( "Gisele" );
			lblGisele.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
			lblGisele.setHorizontalAlignment( SwingConstants.CENTER );
			lblGisele.setForeground( Color.ORANGE );
			lblGisele.setBackground( Color.WHITE );
			lblGisele.setBounds( 2, 190, 264, 33 );
		}
		return lblGisele;
	}
}
