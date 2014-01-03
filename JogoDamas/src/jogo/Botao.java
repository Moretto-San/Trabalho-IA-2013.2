package jogo;

import javax.swing.JButton;

public class Botao extends JButton {

	private static final long serialVersionUID = 1L;
	private int i, j;
	private Cor cor;
	private boolean ocupado = false;
	private boolean emRisco = false;

	public Botao(int i, int j){
		this.i = i;
		this.j = j;		
	}
	
	public Botao(int i, int j, Cor cor){
		this.i = i;
		this.j = j;
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	public boolean isEmRisco() {
		return emRisco;
	}

	public void setEmRisco(boolean emRisco) {
		this.emRisco = emRisco;
	}
}