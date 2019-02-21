package files;

import java.io.Serializable;

public class FileInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fileName;
	private String path;
	public FileInformation(String p_name, String p_path) {
		this.fileName = p_name;
		this.path = p_path;
	}
	public String getFileName() {
		return fileName;
	}
	public String getPath() {
		return path;
	}

}
