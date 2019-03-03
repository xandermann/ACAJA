package gui.processing;

public class Form {
	
	private int[] tab;
	private char typeCommande;
	
	public Form(int[] t, char tyC) {
		this.tab = t;
		this.typeCommande = tyC;
	}

	public int[] getTab() {
		return tab;
	}
	public char getTypeCommande() {
		return typeCommande;
	}

}
