package tests.files;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import files.SelectableFile;

public class SelectaleFileTest {

	/**
	 * Test
	 * 
	 * @throws Exception
	 */
	@Test
	public void fichierVideoTest() throws Exception {
		File file = new File("Test.mp4");
		SelectableFile selectableFile = new SelectableFile(file);

		if (selectableFile.getTypeFile() == SelectableFile.FILE_TYPE_VIDEO) {
			// OK
		} else {
			throw new Exception("Erreur fonction whoIAm()");
		}
	}
	
	/**
	 * Test
	 * 
	 * @throws Exception
	 */
	@Test
	public void fichierAudioTest() throws Exception {
		File file = new File("Test.mp3");
		SelectableFile selectableFile = new SelectableFile(file);

		if (selectableFile.getTypeFile() == SelectableFile.FILE_TYPE_AUDIO) {
			// OK
		} else {
			throw new Exception("Erreur fonction whoIAm()");
		}
	}
	
	/**
	 * Test
	 * 
	 * @throws Exception
	 */
	@Test
	public void fichierImageTest() throws Exception {
		File file = new File("Test.png");
		SelectableFile selectableFile = new SelectableFile(file);

		if (selectableFile.getTypeFile() == SelectableFile.FILE_TYPE_IMAGE) {
			// OK
		} else {
			throw new Exception("Erreur fonction whoIAm()");
		}
	}

}
