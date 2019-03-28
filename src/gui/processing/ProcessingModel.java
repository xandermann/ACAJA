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
	private String destinationFolder;
	private String message;
	//private Form f;
	
	
	public ProcessingModel() {
		Context.$M = this;
		this.fUp = false;
		this.cropUp = false;
		setMessage("Veuillez importer votre premier fichier");
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
		this.setMinia(this.currentFile.getThumbnail());
		setMessage("Fichier chargé avec succes !");
		sendChanges();
	}

	public void addForm(int a,int b,int c,int d,char type) {
		int[] tab = new int[4];
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
		tab[3] = d;
		
		if(currentFile != null) {
			boolean containForm = false;
			for(Form form : listRect) {
				if(form.getTypeCommande() == type) {
					form.setForm(tab, type);
					containForm = true;
					break;
				}
			}	
			if(!containForm) {
				Form f = new Form(tab,type);
				listRect.add(f);
			}
			System.out.println(a+"-"+b+"-"+c+"-"+d+"-t:"+type);
		}
			
				
		
		
		
		
		sendChanges();
		
	}
	
	public void suppLastForm() {
		if(!this.listRect.isEmpty())
			this.listRect.remove(this.listRect.size()-1);
		sendChanges();
	}
	
	public void setDestinationFolder(File destinationFolder) {
		this.destinationFolder = destinationFolder.getPath();
	}
	
	public String getDestinationFolder() {
		return destinationFolder;
	}



	@Override
	public void save() throws UnfindableResourceException {
		Form actu = this.listRect.get(this.listRect.size()-1);
		String[] res = currentFile.getResolution().split("x");
		
		double coeffWidth = ((double)Integer.parseInt(res[0]))/500;
		double coeffHeight = ((double)Integer.parseInt(res[1]))/350;
		
		int a1 = (int) (actu.getTab()[0]*coeffWidth);
		int b1 = (int) (actu.getTab()[1]*coeffHeight);
		
		int c1 = (int) (actu.getTab()[2]*coeffWidth);
		int d1 = (int) (actu.getTab()[3]*coeffHeight);
		
		System.out.println(a1+"-"+b1+"-"+c1+"-"+d1);
		
		switch (actu.getTypeCommande()) {
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
		
		UserRequests.execute(currentFile);
		sendChanges();
	}
	
	public void modify(OperationType typeSetting, String setting) {
		this.currentFile.modify(typeSetting, setting);
		this.currentFile.deselect();
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



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
		sendChanges();
	}
}
