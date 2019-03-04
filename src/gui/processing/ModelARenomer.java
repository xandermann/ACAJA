package gui.processing;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import javax.management.ListenerNotFoundException;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.files.SelectableFile;
import wrapper.runtime.global.SystemRequests;

public class ModelARenomer extends Observable{
	
	private ArrayList<Form> listRect;
	private boolean fUp,cropUp;
	private SelectableFile curentFile;
	private File minia;
	
	
	public ModelARenomer() {
		this.fUp = false;
		this.cropUp = false;
		listRect = new ArrayList<>();
	}

	
	
	public File getMinia() {
		return minia;
	}
	public void setMinia(File minia) {
		this.minia = minia;
	}
	public boolean isfUp() {
		return fUp;
	}
	public void setfUp(boolean fUp) {
		this.fUp = fUp;
	}
	public boolean iscropUp() {
		return cropUp;
	}
	public void setcropUp(boolean rectUp) {
		this.cropUp = rectUp;
	}
	public ArrayList<Form> getListRect() {
		return listRect;
	}
	public char getType(int pos) {
		return listRect.get(pos).getTypeCommande();
	}
	public int[] getTabInt(int pos) {
		return listRect.get(pos).getTab();
	}
	public SelectableFile getCurentFile() {
		return curentFile;
	}
	public void setCurentFile(SelectableFile curentFile) {
		this.curentFile = curentFile;
		this.setMinia(this.curentFile.getMiniature());
	}

	public void addForm(int a,int b,int c,int d,char type) {
		int[] tab = new int[4];
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
		tab[3] = d;
		
		Form f = new Form(tab,type);
		listRect.add(f);
		System.out.println(a+"-"+b+"-"+c+"-"+d+"-t:"+type);
		this.notifyObservers();
		this.setChanged();
	}
	
	public void suppLastForm() {
		if(!this.listRect.isEmpty())
			this.listRect.remove(this.listRect.size()-1);
	}



	


}
