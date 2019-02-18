package gui.conversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public final class EntryHistory {
// TODO : MANAGE MULTIPLE FILENAMES
// TODO : CODE OPTIMISATION
	// TODO : FIX > 9 MANAGEMENT
	
	
	//using Singleton design pattern to avoid 
	//multiple EntryHistory instantiation
	// 1 history / conversion window
	private static volatile EntryHistory history = null;
	
	// key is path, value is filename
	private FileInformation[] files;
	
	// private constructor
	private EntryHistory() {
		super();
		// maximum 10 last files
		this.files = new FileInformation[10];
	}
	
	
	public synchronized final static EntryHistory createInstance() {
		if(EntryHistory.history == null) 
				EntryHistory.history = new EntryHistory();
		
		//getting file history
		EntryHistory.history.getMediaHistory();
		return EntryHistory.history;
	}
	
	public FileInformation[] getFiles() {
		return this.files;
	}
	
	
	public void getMediaHistory() {
		try {
			File dir = new File("entries");
			if(!dir.exists())
				dir.mkdir();
			
			File[] files = dir.listFiles();
			for(int i = 0 ; i < files.length ; i++) {
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);
				if(i < 9)  // avoid array out of bounds
					this.files[i] = (FileInformation)ois.readObject();
				ois.close();
				fis.close();
			}
		} catch(SecurityException se) {
			System.out.println(se.getMessage());
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void addMediaToHistory(FileInformation f) {
		
		for(int i = 0; i < this.files.length; i++) {
		    if(this.files[i] == null) {
		    	System.out.println("found null at "+i);
		        this.files[i] = f;
		        this.saveMediaHistory();
		        break;
		    }  
		    if(i==9) {
		    	System.out.println("size goto 9");
		    	if(deleteFile(this.files[0].getFileName())) {
		    		//remove first occurence and moving all others
		    
		    		System.out.println("file deleted");
		    		for(int j = 1 ; j < this.files.length ; j ++)
		    			this.files[j-1] = this.files[j];
		    		//add new fileinformation at last position
		    		this.files[9] = f;
		    	this.saveMediaHistory();	
		    	}
		    }
		}
	}
	
	public void saveMediaHistory() {
		try {
			for(FileInformation fi : this.files) {
				File f = new File("entries/" + fi.getFileName() + ".acara");
				int fileNumber = 0;
				if(!f.exists()) {
					FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(fi);
					oos.close();
					fos.close();
				}	
			}
		} catch(SecurityException se) {
			System.out.println(se.getMessage());
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean deleteFile(String fileName) {
		try {
			File f = new File("entries/" + fileName);
			if(!(f.exists())) 
				return false;
			return f.delete();
		}catch(SecurityException se) {
			return false;
		} catch(Exception e) {
			return false;
		}
	}
	
}
