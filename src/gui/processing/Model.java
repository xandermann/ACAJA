package gui.processing;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
	
	private ArrayList<Form> listRect;
	
	public Model() {
		listRect = new ArrayList<>();
	}

	
	
	public ArrayList<Form> getListRect() {
		return listRect;
	}

	public void addRect(int a,int b,int c,int d,char type) {
		int[] tab = new int[4];
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
		tab[3] = d;
		
		Form f = new Form(tab,type);
		listRect.add(f);
		System.out.println("x2:"+a+"-y2"+b+"-x1:"+c+"-y1:"+d);
		this.notifyObservers();
		this.setChanged();
	}


}
