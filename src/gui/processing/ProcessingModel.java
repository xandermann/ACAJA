package gui.processing;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import javax.management.ListenerNotFoundException;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.OperationType;
import files.enumerations.ProcessingType;
import files.enumerations.SettingType;
import files.files.ProcessingFile;
import files.files.SelectableFile;
import gui.Model;
import wrapper.runtime.global.SystemRequests;
import wrapper.runtime.global.UserRequests;

public class ProcessingModel extends Model{
	
	private ArrayList<Form> listRect;
	private boolean fUp,cropUp;
	private ProcessingFile curentFile;
	private File minia;
	
	
	public ProcessingModel() {
		this.fUp = false;
		this.cropUp = false;
		listRect = new ArrayList<>();
	}

	
	
	public File getMinia() {
		return minia;
	}
	public void setMinia(File minia) {
		this.minia = minia;
		sendChanges();
	}
	public boolean isfUp() {
		return fUp;
	}
	public void setfUp(boolean fUp) {
		this.fUp = fUp;
		sendChanges();
	}
	public boolean iscropUp() {
		return cropUp;
	}
	public void setcropUp(boolean rectUp) {
		this.cropUp = rectUp;
		sendChanges();
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
	public void setCurentFile(ProcessingFile curentFile) {
		this.curentFile = curentFile;
		this.setMinia(this.curentFile.getThumbail());
		sendChanges();
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
		sendChanges();
		this.modify(ProcessingType.CROPED,a+" "+b+" "+c+" "+d );
	}
	
	public void suppLastForm() {
		if(!this.listRect.isEmpty())
			this.listRect.remove(this.listRect.size()-1);
		sendChanges();
	}



	@Override
	public void save() throws UnfindableResourceException {
		//if(this.curentFile.isModified()) {
			UserRequests.execute(curentFile);
		//}
	}
	
	public void modify(OperationType typeSetting, String setting) {
		this.curentFile.modify(typeSetting, setting);
	}



	


}
