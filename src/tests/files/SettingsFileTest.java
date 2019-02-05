package tests.files;

import java.io.File;

import org.junit.Test;

import exceptions.IncorrectFileException;
import files.SettingsFile;

public final class SettingsFileTest {

	/**
	 * Test le constructeur
	 * 
	 * La classe n'est pas encore instanciable
	 * 
	 * TODO
	 * 
	 * Quand cela pourra être possible
	 */

	/**
	 * TODO
	 * 
	 * Constructeur OK
	 * 
	 * @throws IncorrectFileException 
	 */
	@Test
	public void constructeurOKTest() throws IncorrectFileException {
		SettingsFile sf = new SettingsFile(new File("A.mp3"));
	}

	/**
	 * TODO
	 * 
	 * Constructeur mauvais
	 * 
	 * @throws IncorrectFileException 
	 */
	@Test
	public void constructeurMauvaisTest() throws IncorrectFileException {
		SettingsFile sf = new SettingsFile(new File("A.txt"));
	}

}
