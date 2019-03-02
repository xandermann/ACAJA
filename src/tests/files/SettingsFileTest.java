package tests.files;

import java.io.File;

import org.junit.Test;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.files.SettingsFile;

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
	 * 
	 * @throws UnfindableResourceException 
	 */
	@Test
	public void constructeurOKTest() throws IncorrectFileException, UnfindableResourceException {
		SettingsFile sf = new SettingsFile(new File("A.mp3"));
	}

	/**
	 * TODO
	 * 
	 * Constructeur mauvais
	 * 
	 * @throws IncorrectFileException 
	 * 
	 * @throws UnfindableResourceException 
	 */
	@Test
	public void constructeurMauvaisTest() throws IncorrectFileException, UnfindableResourceException {
		SettingsFile sf = new SettingsFile(new File("A.txt"));
	}

}
