package tests.files;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import files.SelectableFile;

public class SelectaleFileTest {

	/**
	 * Tests constructeur
	 */

	/**
	 * Ne renvoie pas d'exception
	 * 
	 * Les 3 fichiers sont crées correctement
	 */
	@Test
	public void constructeurOkTest() {
		SelectableFile file = new SelectableFile(new File("Test.mp4"));
		SelectableFile file2 = new SelectableFile(new File("Test.mp3"));
		SelectableFile file3 = new SelectableFile(new File("Test.jpeg"));
	}

	/**
	 * Renvoie une erreur car l'extension est invalide
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructeurErreurTest() {
		SelectableFile file = new SelectableFile(new File("Test.superExtension"));
	}

	// =======================================================================================================================

	/**
	 * Tests containsAudio()
	 */

	/**
	 * Test avec video et audio
	 */
	@Test
	public void isGoodFileOKTest() {
		SelectableFile selectableFileVideo = new SelectableFile(new File("Test.mp4"));
		SelectableFile selectableFileAudio = new SelectableFile(new File("Test.mp3"));

		assertTrue("Devrait être accepté", selectableFileVideo.containsAudio());
		assertTrue("Devrait être accepté", selectableFileAudio.containsAudio());
	}

	/**
	 * Test avec image Nous ne pouvons pas tester les autres formats car ils sont
	 * rejetés dans le constructeur
	 */

	@Test
	public void isGoodFileTest() {
		SelectableFile selectableFileImage = new SelectableFile(new File("Test.png"));

		assertFalse("Devrait ne pas être accepté", selectableFileImage.containsAudio());
	}

	// =======================================================================================================================

	/**
	 * Tests whoIAm();
	 */

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
