package gui.processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.OperationType;
import files.enumerations.ProcessingType;
import files.files.ProcessingFile;
import files.files.SelectableFile;
import gui.JFileChooserManager;
import gui.alerts.Alert;
import gui.alerts.AlertWindow;
import gui.general.Context;
import gui.general.GeneralModel;
import resources.NamesSpaceManager;
import threads.RuntimeSpaceManager;
import threads.ThreadForSave;
import threads.ThreadForWaitWindow;
import wrapper.runtime.details.Request;

public class ProcessingModel extends GeneralModel{
	private List<Form> forms;
	private ProcessingFile currentFile;
	private File minia;
	private String destinationFolder;
	private boolean modeBlur,modeCrop, rotateLeft, rotateRight, rotate180Left, rotate180Right, rotate180;
	private ArrayList<File> images;
	private Form currentForm;
	
	public ArrayList<File> getImages() {
		return images;
	}


	public void setImages(ArrayList<File> images) {
		this.images = images;
	}


	public ProcessingModel() {
		Context.$M = this;
		init();
	}

	
	private void init(){
		modeBlur = false;
		modeCrop = false;
		rotateLeft = false;
		rotateRight = false;
		rotate180Left = false;
		rotate180Right = false;
		rotate180 = false;
		forms = new ArrayList<Form>();
		images = new ArrayList<File>();
	}
	
	
	public File getMinia() {
		return minia;
	}
	
	
	public void setMinia(File minia) {
		this.minia = minia;
		sendChanges();
	}
	
	
	public boolean isModeBlur() {
		return modeBlur;
	}
	
	
	public void setModeBlur(boolean modeBlur) {
		this.modeBlur = modeBlur;
		sendChanges();
	}
	
	
	public boolean isModeCrop() {
		return modeCrop;
	}
	
	
	public void setModeCrop(boolean rectUp) {
		this.modeCrop = rectUp;
		sendChanges();
	}
	
	
	public List<Form> getForms() {
		return forms;
	}
	
	
	public char getType(int pos) {
		return forms.get(pos).getFormType();
	}
	
	
	public int[] getTabInt(int pos) {
		return forms.get(pos).getFormValues();
	}
	
	
	public ProcessingFile getCurrentFile() {
		return currentFile;
	}
	
	
	public void setCurrentFile(SelectableFile currentFile) {
		if(currentFile != null) {
			this.currentFile = (ProcessingFile) currentFile;
			this.setMinia(this.currentFile.getThumbnail());
			sendChanges();
			if(this.currentFile == null)
				Alert.shortAlert(Alert.FAILURE, "Suppression de la video realisee avec succes.");
			else
				Alert.shortAlert(Alert.SUCCESS, "La video a ete importee avec succes.");
		} else {
			Alert.shortAlert(Alert.FAILURE, "Action impossible.");
		}
	}

	
	
	public void resizeForm(Form f, int x, int y, int width, int height) {}
	
	

	public void addForm(int a,int b,int c,int d,char type,File i) {
		int[] tab = new int[4];
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
		tab[3] = d;
		if(currentFile != null) {
			boolean containForm = false;
			for(Form form : forms) {
				if(form.getFormType() == type) {
					if(i != null) {
						form.setForm(tab, type,i);
						currentForm = form;
					}
					else
						form.setForm(tab, type,null);
					
					containForm = true;
					currentForm = form;
					break;
				}
			}	
			if(!containForm) {
				Form f;
				if(i != null)
					f = new Form(tab,type,i);
				else
					f = new Form(tab,type,null);
				forms.add(f);
				currentForm = f;
				System.out.println(a+"-"+b+"-"+c+"-"+d+"-t:"+type);
				
			}
		}
		sendChanges();
		
	}
	

	
	public void clearProcessings() {
		if(!this.forms.isEmpty()) {
			init();
			sendChanges();
		}
	}
	
	
	
	public void setDestinationFolder(File destinationFolder) {
		this.destinationFolder = destinationFolder.getPath();
	}
	
	
	
	public String getDestinationFolder() {
		return destinationFolder;
	}



	@Override
	public void save() throws UnfindableResourceException {
		if(currentFile != null) {
			boolean rotation = false;
			boolean blur = false;
			boolean crop = false;
			boolean image = false;
			boolean verticalMode = false;
			boolean invertedVerticalMode = false;
			
			if(rotateLeft || rotateRight || rotate180Left || rotate180Right || rotate180) rotation = true;
			if(rotateLeft) this.modify(ProcessingType.ROTATED, "left");
			if(rotateRight) this.modify(ProcessingType.ROTATED, "right");
			if(rotate180Left) this.modify(ProcessingType.ROTATED, "180left");
			if(rotate180Right) this.modify(ProcessingType.ROTATED, "180right");
			if(rotate180) this.modify(ProcessingType.ROTATED, "180");
			if(rotateLeft || rotateRight) verticalMode = true;
			if(rotate180Left || rotate180Right) invertedVerticalMode = true;
			
			String[] actualResolution = currentFile.getResolution().split("x");
			double actualWidth = Double.parseDouble(actualResolution[0]);
			double actualHeight = Double.parseDouble(actualResolution[1]);
			double virtualWidth = (new PictureVisualView()).getWidth();
			double virtualHeight = (new PictureVisualView()).getHeight();
			
			double coeffWidth = 0;
			double coeffHeight = 0;
			if(verticalMode || invertedVerticalMode) {
				actualWidth = Double.parseDouble(actualResolution[1]);
				actualHeight = Double.parseDouble(actualResolution[0]);
				virtualWidth = (new PictureVisualView()).getHeight();
				virtualHeight = (new PictureVisualView()).getWidth();
				coeffWidth = actualWidth/virtualWidth;
				coeffHeight = actualHeight/virtualHeight;
			} else {
				coeffWidth = actualWidth/virtualWidth;
				coeffHeight = actualHeight/virtualHeight;
			}

			if(!forms.isEmpty()) {
				for(Form f : forms) {
					int[] formValues = f.getFormValues();
					String S = "|";
					int x = (int) (formValues[0]*coeffWidth);
					int y = (int) (formValues[1]*coeffHeight);	
					int width = (int) (formValues[2]*coeffWidth);
					int height = (int) (formValues[3]*coeffHeight);
					switch (f.getFormType()) {
					case 'c':
						this.modify(ProcessingType.CROPED,x+S+y+S+width+S+height);
						crop = true;
						break;
					case 'f':
						this.modify(ProcessingType.BLURRED,x+S+y+S+width+S+height);
						blur = true;
						break;
					case 'i':
						String output = NamesSpaceManager._temporary();
						new Request(f.getFormImage().getAbsolutePath(),output).resize(""+width, ""+height).make();
						this.modify(ProcessingType.ADDED_IMAGE, output+S+x+S+y);
						image = true;
						break;
					}
				}
			}
			

		if(currentFile.isModified()) {
			destinationFolder = JFileChooserManager.chooseDirectory().getAbsolutePath();
			currentFile.setDestinationPath(getDestinationFolder());
			currentFile.setFileExtension(currentFile.getSourceFileExtension());
			if(rotation && crop &&  !image && !blur) {
				System.out.println("Rotation et rogner");
				ProcessThreadManager.treatTwoProcesses(currentFile, ProcessingType.CROPED);	
			} else if (rotation && blur && !crop && !image) {
				System.out.println("Rotation et flou");
				ProcessThreadManager.treatTwoProcesses(currentFile, ProcessingType.BLURRED);	
			} else if (crop && blur && !rotation && !image) {
				System.out.println("Rogner et flouter");
				ProcessThreadManager.treatTwoProcesses(currentFile,ProcessingType.CROPED);
			} else if (image && rotation && !crop && !blur) {
				System.out.println("Image et rotation");
				ProcessThreadManager.treatTwoProcesses(currentFile,ProcessingType.ROTATED);
			} else if (image && crop && !blur && !rotation) {
				System.out.println("Image et crop");
				ProcessThreadManager.treatTwoProcesses(currentFile,ProcessingType.CROPED);
			} else if (image && blur && !rotation && !crop) {
				System.out.println("Image et flou");
				ProcessThreadManager.treatTwoProcesses(currentFile,ProcessingType.BLURRED);
			} else if ( (image && !crop && !blur && !rotation) || (!image && crop && !blur && !rotation) || (!image && !crop && blur && !rotation) || (!image && !crop && !blur && rotation)) {
				System.out.println("Une seule action");
				ProcessThreadManager.treatOneProcess(currentFile);
			}else {
				Alert.shortAlert(Alert.FAILURE, "La video ne peut subir plus<br> de deux traitements a la fois");
			}
		}else 
			Alert.shortAlert(Alert.FAILURE, "La video n'a subit aucun traitement,<br>l'exporter n'aurait aucuns sens.");
	}

	}
	
	private void processFile(ProcessingFile f) {
		new Thread() {
			public void run (){
				/**
				 * ATTENDRE QU'ON ME RENDE LA MAIN.
				 */
				while(RuntimeSpaceManager.hand.took()) Thread.yield();
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
				ThreadForSave.saveInNewThread(f);
				/**
				 * LANCEMENT ET GESTION DE LA FENETRE D'ATTENTE DANS UN AUTRE PROCESSUS.
				 */
				ThreadForWaitWindow.waitInNewThread(
						new AlertWindow(
								AlertWindow.INFO,
								"Traitement du fichier "+f.getSourceFileName()+"<br>Veuillez patientez..."),
						f.getSourceFile());
			}
		}.start();
	}
	
	
	
	public void modify(OperationType typeSetting, String setting) {
		this.currentFile.modify(typeSetting, setting);
		sendChanges();
	}


	@Override
	public boolean isModified() {
		return currentFile.isModified();
	}

	
	@Override
	public void remove(SelectableFile file) {
		if(file != null) {
			images.remove(file);
			sendChanges();
			Alert.shortAlert(Alert.SUCCESS, "L'image a ete supprime avec succes.");
		}else
			Alert.shortAlert(Alert.INFO, "Aucun fichier a supprimer.");
	}

	
	@Override
	public void clear() {
		if(!images.isEmpty()) {
			images.clear();		
			sendChanges();
			Alert.shortAlert(Alert.SUCCESS, "Bibliotheque videe avec succes.");
		}else
			Alert.shortAlert(Alert.INFO, "La bibliotheque est deja vide.");
	}

	
	public boolean isRotateLeft() {
		return rotateLeft;
	}


	public void setRotateLeft(boolean rotateLeft) {
		this.rotateLeft = rotateLeft;
	}


	public boolean isRotateRight() {
		return rotateRight;
	}


	public void setRotateRight(boolean rotateRight) {
		this.rotateRight = rotateRight;
	}
	
	public void addImage(File image) {
		if(image != null) {
			this.images.add(image);
			sendChanges();
			Alert.shortAlert(Alert.SUCCESS, "Image importee avec succes.");
		}
	}


	@Override
	public void add(File file) throws IncorrectFileException, UnfindableResourceException, FileNotFoundException {}


	@Override
	public List<SelectableFile> getFiles() {
		return null;
	}


	public boolean isRotate180Left() {
		return rotate180Left;
	}


	public void setRotate180Left(boolean rotate180Left) {
		this.rotate180Left = rotate180Left;
	}


	public boolean isRotate180Right() {
		return rotate180Right;
	}


	public void setRotate180Right(boolean rotate180Right) {
		this.rotate180Right = rotate180Right;
	}



	public boolean isRotate180() {
		return rotate180;
	}


	public void setRotate180(boolean rotate180) {
		this.rotate180 = rotate180;
	}


	public Form getCurrentForm() {
		return currentForm;
	}


	public void setCurrentForm(Form currentForm) {
		this.currentForm = currentForm;
	}


	@Override
	public String getValue(OperationType typeChange) {
		return currentFile.getValue(typeChange);
	}

}
