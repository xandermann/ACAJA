package tests.files;

import java.io.File;

import org.junit.Test;

import files.SettingsFile;

public class SettingsFileTest {

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
	 */
	@Test
	public void constructeurOKTest() {
		SettingsFile sf = new SettingsFile(new File("A.mp3"));
	}

	/**
	 * TODO
	 * 
	 * Constructeur mauvais
	 */
	@Test
	public void constructeurMauvaisTest() {
		SettingsFile sf = new SettingsFile(new File("A.txt"));
	}

}
