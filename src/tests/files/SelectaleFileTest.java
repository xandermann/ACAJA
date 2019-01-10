package tests.files;

import java.io.File;

import org.junit.Test;

import files.SelectableFile;

public class SelectaleFileTest {

	/**
	 * Test si le programme detecte bien les fichiers vidéos
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
	 * Test si le programme detecte bien les fichiers audios
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
	 * Test si le programme detecte bien les fichiers images
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

	/**
	 * Test si le programme detecte bien les fichiers invalide
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void fichierInvalideTest() throws Exception {
		File file = new File("Test.superextension");
		SelectableFile selectableFile = new SelectableFile(file);

	}

}
