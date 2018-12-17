package files;

import java.io.File;
import java.util.HashMap;

public class SettingsFile {

	private boolean isVideo;
	private HashMap<String, Object> oldSettings;
	private HashMap<String, Object> settings;

	public SettingsFile(File file, boolean isVideo) {
		// this.file
		this.isVideo = isVideo;
	}

	public void modifySettings(String s, Object o) {

	}

	public boolean isVideo() {
		return this.isVideo;
	}

	public boolean isModified() {
		return !this.oldSettings.equals(this.settings);
	}

	public HashMap<String, Object> getSettings() {
		return this.settings;
	}

}
