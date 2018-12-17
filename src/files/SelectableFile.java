package files;

import java.io.File;

public class SelectableFile {

	protected File sourceFile;

	private boolean isSelected;

	public void SelectionFile(File file) {
		this.sourceFile = file;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
