package gui.processing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.management.ListenerNotFoundException;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.OperationType;
import files.enumerations.ProcessingType;
import files.enumerations.SettingType;
import files.files.ProcessingFile;
import files.files.SelectableFile;
import gui.general.Context;
import gui.general.GeneralModel;
import wrapper.runtime.global.SystemRequests;
import wrapper.runtime.global.UserRequests;

public class ProcessingModel extends GeneralModel{
	
	private List<Form> listRect;
	private boolean fUp,cropUp;
	private ProcessingFile currentFile;
	private File minia;
	
	
	public ProcessingModel() {
		Context.$M = this;
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
	public List<Form> getListRect() {
		return listRect;
	}
	public char getType(int pos) {
		return listRect.get(pos).getTypeCommande();
	}
	public int[] getTabInt(int pos) {
		return listRect.get(pos).getTab();
	}
	public SelectableFile getCurrentFile() {
		return currentFile;
	}
	public void setCurrentFile(SelectableFile currentFile) {
		this.currentFile = (ProcessingFile) currentFile;
		this.setMinia(this.currentFile.getThumbail());
		this.currentFile.setDestinationPath(".");
		this.currentFile.setDestinationName("traitement");
		this.currentFile.setFileExtension(".mp4");
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
		String[] res = currentFile.getResolution().split("x");
		
		double coeffWidth = ((double)Integer.parseInt(res[0]))/500;
		double coeffHeight = ((double)Integer.parseInt(res[1]))/350;
		System.out.println(coeffWidth+" "+coeffHeight);
		
		int a1 = (int) (a*coeffWidth);
		int b1 = (int) (b*coeffHeight);
		
		int c1 = (int) (c*coeffWidth);
		int d1 = (int) (d*coeffHeight);
		
		System.out.println(a1+"-"+b1+"-"+c1+"-"+d1);
		
		switch (type) {
		case 'c':
			this.modify(ProcessingType.CROPED,a1+" "+b1+" "+c1+" "+d1 );
			break;
		case 'f':
			this.modify(ProcessingType.BLURRED,a1+" "+b1+" "+c1+" "+d1 );
			break;

		default:
			System.out.println("Non implementé");
			break;
		}
		sendChanges();
		
	}
	
	public void suppLastForm() {
		if(!this.listRect.isEmpty())
			this.listRect.remove(this.listRect.size()-1);
		sendChanges();
	}



	@Override
	public void save() throws UnfindableResourceException {
		//if(this.currentFile.isModified()) {
			UserRequests.execute(currentFile);
			sendChanges();
		//}
	}
	
	public void modify(OperationType typeSetting, String setting) {
		this.currentFile.modify(typeSetting, setting);
		sendChanges();
	}



	@Override
	public boolean isModified() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void add(File file) throws IncorrectFileException, UnfindableResourceException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void remove(SelectableFile file) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<SelectableFile> getFiles() {
		// TODO Auto-generated method stub
		return null;
	}
}
