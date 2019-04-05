package tests.files;

import java.io.File;

import org.junit.Test;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.files.ProcessingFile;

public final class ProcessingFileTest {

	@Test
	public void test() throws IncorrectFileException, UnfindableResourceException {
		ProcessingFile pf = new ProcessingFile(new File("a.txt"));
	}

}
