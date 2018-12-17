package gui_conversion;

import java.io.*;
import java.util.*;
import files.*;

public class ConversionModel {

	private SettingsFile currentFile;
	private ArrayList<SettingsFile> files;
	
	
	public ConversionModel() {
		
	}
	
	public void loadProfile(File profileFile) {
		
	}

	public SettingsFile getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(SettingsFile currentFile) {
		this.currentFile = currentFile;
	}
	
	
}
