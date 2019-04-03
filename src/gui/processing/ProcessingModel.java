package gui.processing;

import java.awt.Image;
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
import gui.alerts.AlertWindow;
import gui.general.Context;
import gui.general.GeneralModel;
import resources.NamesSpaceManager;
import threads.RuntimeSpaceManager;
import threads.ThreadForSave;
import threads.ThreadForWaitWindow;
import wrapper.runtime.details.Request;

public class ProcessingModel extends GeneralModel{
	private List<Form> listRect;
	private boolean fUp,cropUp;
	private ProcessingFile currentFile;
	private File minia;
	private String destinationFolder;
	private boolean rotateLeft, rotateRight, rotate180Left, rotate180Right, rotate180, rotation;
	private ArrayList<File> images;
	private String cropOrBlurSetting;
	private boolean verticalMode;
	
	public ArrayList<File> getImages() {
		return images;
	}


	public void setImages(ArrayList<File> images) {
		this.images = images;
	}


	public ProcessingModel() {
		Context.$M = this;
		fUp = false;
		cropUp = false;
		rotateLeft = false;
		rotateRight = false;
		rotate180Left = false;
		rotate180Right = false;
		rotate180 = false;
		verticalMode = false;
		cropOrBlurSetting = "";
		listRect = new ArrayList<>();
		images = new ArrayList<File>();
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
		sendChanges();
	}

	
	public void addForm(int a,int b,int c,int d,char type,File i) {
		int[] tab = new int[4];
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
		tab[3] = d;
		
		if(currentFile != null) {
			boolean containForm = false;
			for(Form form : listRect) {
				if(form.getTypeCommande() == type) {
					if(i != null) {
						form.setForm(tab, type,i);
					}
					else
						form.setForm(tab, type,null);
					
					containForm = true;
					break;
				}
			}	
			if(!containForm) {
				Form f;
				if(i != null)
					f = new Form(tab,type,i);
				else
					f = new Form(tab,type,null);
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
			case 'i':
				String output = NamesSpaceManager._temporary();
				new Request(actu.getImageA().getAbsolutePath(),output).resize(""+c1, ""+d1).make();
				this.modify(ProcessingType.ADDED_IMAGE, output+" "+a1+" "+b1);
				break;
			default:
				System.out.println("Non implemente");
				break;
			}
			
		}

		rotation = false;
	
		
		if(rotateLeft) {	

			this.modify(ProcessingType.ROTATED, "left");
			rotation = true;
			
		} 
		if(rotateRight) {
			this.modify(ProcessingType.ROTATED, "right");
			rotation = true;
		}
		if(rotate180Left) {
			this.modify(ProcessingType.ROTATED, "180left");
			rotation = true;
		}
		if(rotate180Right) {
			this.modify(ProcessingType.ROTATED, "180right");
			rotation = true;
		}
		if(rotate180) {
			this.modify(ProcessingType.ROTATED, "180");
			rotation = true;
		}
		
		if(!listRect.isEmpty()) {
			Form actu = this.listRect.get(this.listRect.size()-1);
			String[] res = currentFile.getResolution().split("x");
			
			double coeffWidth = ((double)Integer.parseInt(res[0]))/500;
			double coeffHeight = ((double)Integer.parseInt(res[1]))/350;
			
			int x = (int) (actu.getTab()[0]*coeffWidth);
			int y = (int) (actu.getTab()[1]*coeffHeight);
			
			int width = (int) (actu.getTab()[2]*coeffWidth);
			int height = (int) (actu.getTab()[3]*coeffHeight);
			
			cropOrBlurSetting = x+" "+y+" "+width+" "+height;
		
			
				switch (actu.getTypeCommande()) {
				case 'c':
					this.modify(ProcessingType.CROPED,cropOrBlurSetting);
					
					break;
				case 'f':
					this.modify(ProcessingType.BLURRED,cropOrBlurSetting);
					break;
				case 'i':
					this.modify(ProcessingType.ADDED_IMAGE, actu.getImageA()+" "+x+" "+y);
					break;
		
				default:
					System.out.println("Non implemente");
					break;
				}
			}
		
			
	if(currentFile.isModified()) {
		if(rotation && cropUp) {
			System.out.println("Rotation et crop");
			currentFile.cancel(ProcessingType.CROPED);
			File dest =  JFileChooserManager.chooseDirectory();
			setDestinationFolder(dest);
			//getCurrentFile().setDestinationPath(getDestinationFolder());
			getCurrentFile().setDestinationName("tmp_process_file");
			getCurrentFile().setFileExtension(getCurrentFile().getSourceFileExtension());
			String path = getDestinationFolder()+ File.separator + "tmp_process_file" + getCurrentFile().getSourceFileExtension();
			processFile(getCurrentFile());	
		/*	
		 * En cas de probleme avec les threads
		 * 
		 * boolean loaded = false;
			while(!loaded) {
				try {
					setCurrentFile(new ProcessingFile(new File(path)));
					loaded = true;
				} catch (Exception e) { }
			} */
			try {
				ProcessingFile secondFile = null;
				boolean loaded = false;
				while(!loaded) {
					try {
						secondFile = new ProcessingFile(new File(path));
						loaded = true;
					} catch (Exception e) { }
				} 
				secondFile = new ProcessingFile(new File(path));
				secondFile.modify(ProcessingType.CROPED,cropOrBlurSetting);
				secondFile.setDestinationPath(getDestinationFolder());
				secondFile.setDestinationName("new_process_file");
				secondFile.setFileExtension(getCurrentFile().getSourceFileExtension());
				processFile(secondFile);
				File f = new File(path);
				f.delete();
			} catch (IncorrectFileException ife) {} catch (Exception e) { }
			
		} else if (rotation && fUp) {
			System.out.println("Rotation et flou");
			
		} else {
			setDestinationFolder(JFileChooserManager.chooseDirectory());
			getCurrentFile().setDestinationPath(getDestinationFolder());
			getCurrentFile().setDestinationName("Traitement"+System.currentTimeMillis());
			getCurrentFile().setFileExtension(getCurrentFile().getSourceFileExtension());
			processFile(getCurrentFile());
			System.out.println("Autre cas.");
			
		}
	}
			
		
	}
	
	private void processFile(ProcessingFile f) {
		/******************************************************************************************
		 ******************************************************************************************
		 ******************************************************************************************
		 *******************************************************************************************
		 ******************************************************************************************
		 *******************************************************************************************
		 ******************************************************************************************
		 */
		new Thread() {
			public void run (){
				/**
				 * ATTENDRE QU'ON ME RENDE LA MAIN.
				 */
				System.out.println("");
				
				while(RuntimeSpaceManager.hand.took());
				
				System.out.println("");
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
		/******************************************************************************************
		 ******************************************************************************************
		 ******************************************************************************************
		 *******************************************************************************************
		 ******************************************************************************************
		 *******************************************************************************************
		 ******************************************************************************************
		 */
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
}
