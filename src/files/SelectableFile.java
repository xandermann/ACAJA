package files;

import java.io.File;

public class SelectableFile {

	/**
	 * Fichier source
	 */
	protected File sourceFile;

	/**
	 * Vrai s'il est selectionne
	 */
	private boolean isSelected;

	/**
	 * Definition a partir du fichier source
	 * @param file Fichier source
	 */
	public void SelectionFile(File file) {
		this.sourceFile = file;
	}
	
	/**
	 * Test si le fichier est selectionne
	 * @return Vrai s'il est selectionne
	 */
	public boolean isSelected() {
		return isSelected;
	}
	
	/**
	 * Test si le fichier est selectionne
	 * @param isSelected
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
