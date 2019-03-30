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
import files.files.Modifiable;
import files.files.ProcessingFile;
import files.files.SelectableFile;
import gui.alerts.AlertWindow;
import gui.general.Context;
import gui.general.GeneralModel;
import threads.RuntimeSpaceManager;
import threads.ThreadForSave;
import threads.ThreadForWaitWindow;
import wrapper.runtime.global.SystemRequests;
import wrapper.runtime.global.UserRequests;

public class ProcessingModel extends GeneralModel{
	private List<SelectableFile> images;
	private List<Form> listRect;
	private boolean fUp,cropUp;
	private ProcessingFile currentFile;
	private File minia;
	private String destinationFolder;
	private String message;
	private boolean rotateLeft, rotateRight;
	
	public ProcessingModel() {
		Context.$M = this;
		fUp = false;
		cropUp = false;
		setRotateLeft(false);
		setRotateRight(false);
		setMessage("Veuillez importer votre premier fichier");
		listRect = new ArrayList<>();
		images = new ArrayList<SelectableFile>();
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
	
	
	public ProcessingFile getCurrentFile() {
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
				System.out.println(a+"-"+b+"-"+c+"-"+d+"-t:"+type);
			}
		}
				
		sendChanges();
		
	}
	
	
	
	public void suppLastForm() {
		if(!this.listRect.isEmpty()) {
			this.listRect.clear();;
			this.currentFile.cancelAll();
		}
		
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
		if(!listRect.isEmpty()) {
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
				System.out.println("Non implemente");
				break;
			}
			
		}

		if(rotateLeft) {
			this.modify(ProcessingType.ROTATE, "left");
			
		} 
		if(rotateRight) {
			this.modify(ProcessingType.ROTATE, "right");
			
		}
		if(currentFile.isModified()) {
			System.out.println("REAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			new Thread() {
				public void run (){
					/**
					 * ATTENDRE QU'ON ME RENDE LA MAIN.
					 */
					while(RuntimeSpaceManager.hand.took());
					/**
					 * DEBUT DU TRAITEMENT :
					 * 
					 * Prendre la main sur l'espace d'execution.
					 * Prendre la main sur ffmpeg.
					 */
					RuntimeSpaceManager.hand.take();
					startSave();
					/**
					 * LANCEMENT DU TRAITEMENT DANS UN AUTRE PROCESSUS.
					 */
					ThreadForSave.saveInNewThread(currentFile);
					/**
					 * LANCEMENT ET GESTION DE LA FENETRE D'ATTENTE DANS UN AUTRE PROCESSUS.
					 */
					ThreadForWaitWindow.waitInNewThread(
							new AlertWindow(
									AlertWindow.INFO,
									"Traitement du fichier "+currentFile.getSourceFileName()+"<br>Veuillez patientez..."),
							currentFile.getSourceFile());
				}
			}.start();
		}
	}
	
	
	public void modify(OperationType typeSetting, String setting) {
		this.currentFile.modify(typeSetting, setting);
		this.currentFile.deselect();
		sendChanges();
	}


	@Override
	public boolean isModified() {
		return currentFile.isModified();
	}


	@Override
	public void add(File file) throws IncorrectFileException, UnfindableResourceException {
		images.add(new SelectableFile(file));
	}

	
	@Override
	public void remove(SelectableFile file) {
		images.remove(file);
	}

	
	@Override
	public void clear() {
		images.clear();		
	}

	
	@Override
	public List<SelectableFile> getFiles() {
		return images;
	}


	public String getMessage() {
		return message;
	}

	
	public void setMessage(String message) {
		this.message = message;
		sendChanges();
	}


	public boolean isRotateLeft() {
		return rotateLeft;
	}


	public void setRotateLeft(boolean rotateLeft) {
		this.rotateLeft = rotateLeft;
		sendChanges();
	}


	public boolean isRotateRight() {
		return rotateRight;
	}


	public void setRotateRight(boolean rotateRight) {
		this.rotateRight = rotateRight;
		sendChanges();
	}
}
