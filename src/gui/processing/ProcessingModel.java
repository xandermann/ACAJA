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
import gui.general.Context;
import gui.general.GeneralModel;
import resources.NamesSpaceManager;
import wrapper.runtime.details.Request;

public class ProcessingModel extends GeneralModel{
	private List<Form> forms;
	private boolean modeBlur,modeCrop;
	private ProcessingFile currentFile;
	private File minia;
	private String destinationFolder;
	private boolean rotateLeft, rotateRight, rotate180Left, rotate180Right, rotate180;
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
		modeBlur = false;
		modeCrop = false;
		rotateLeft = false;
		rotateRight = false;
		rotate180Left = false;
		rotate180Right = false;
		rotate180 = false;
		forms = new ArrayList<>();
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
		this.currentFile = (ProcessingFile) currentFile;
		this.setMinia(this.currentFile.getThumbnail());
		sendChanges();
	}

	
	
	public void resizeForm(Form f, int x, int y, int width, int height) {
		
	}
	

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
	
	
	
	public void suppLastForm() {
		if(!this.forms.isEmpty()) {
			this.forms.clear();;
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
		if(rotateLeft || rotateRight ) verticalMode = true;
		if(rotate180Left || rotate180Right) invertedVerticalMode = true;
		
		String[] actualResolution = currentFile.getResolution().split("x");
		double coeffWidth = ((double)Integer.parseInt(actualResolution[0]))/500;
		double coeffHeight = ((double)Integer.parseInt(actualResolution[1]))/350;
		if(verticalMode) {
			coeffWidth = ((double)Integer.parseInt(actualResolution[0]))/350;
			coeffHeight = ((double)Integer.parseInt(actualResolution[1]))/500;
		}
		// TODO invertedVerticalMode
		// TODO invertedVerticalMode
		// TODO invertedVerticalMode
		
		if(!forms.isEmpty()) {
			for(Form f : forms) {
				int[] formValues = f.getFormValues();
				int x = (int) (formValues[0]*coeffWidth);
				int y = (int) (formValues[1]*coeffHeight);	
				int width = (int) (formValues[2]*coeffWidth);
				int height = (int) (formValues[3]*coeffHeight);
				switch (f.getFormType()) {
				case 'c':
					this.modify(ProcessingType.CROPED,x+" "+y+" "+width+" "+height);
					crop = true;
					break;
				case 'f':
					this.modify(ProcessingType.BLURRED,x+" "+y+" "+width+" "+height);
					blur = true;
					break;
				case 'i':
					String output = NamesSpaceManager._temporary();
					new Request(f.getFormImage().getAbsolutePath(),output).resize(""+width, ""+height).make();
					this.modify(ProcessingType.ADDED_IMAGE, output+" "+x+" "+y);
					image = true;
					break;
				default:
					System.out.println("Non implemente");
				break;
				}
			}
				
			}
		
/*	
else if (crop && blur) {
			
		} else if (crop && blur) {
			
		}*/
	if(currentFile.isModified()) {
		destinationFolder = JFileChooserManager.chooseDirectory().getAbsolutePath();
		currentFile.setDestinationPath(getDestinationFolder());
		currentFile.setDestinationName("MaSuperVideo"+System.currentTimeMillis());
		currentFile.setFileExtension(currentFile.getSourceFileExtension());
		if(rotation && crop) {
			System.out.println("Rotation et rogner");
			ProcessThreadManager.treatTwoProcesses(currentFile, ProcessingType.CROPED);	
		} else if (rotation && blur) {
			System.out.println("Rotation et flou");
			ProcessThreadManager.treatTwoProcesses(currentFile, ProcessingType.BLURRED);	
		} else if (crop && blur) {
			System.out.println("Rogner et flouter");
			ProcessThreadManager.treatTwoProcesses(currentFile,ProcessingType.CROPED);
		}  
		else {
			System.out.println("Une seule action");
			ProcessThreadManager.treatOneProcess(currentFile);
		}
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
	public void remove(SelectableFile file) {
		images.remove(file);
	}

	
	@Override
	public void clear() {
		images.clear();		
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
	
	public void addImage(File e) {
		this.images.add(e);
		sendChanges();
	}


	@Override
	public void add(File file) throws IncorrectFileException, UnfindableResourceException, FileNotFoundException {

		
	}


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

}
