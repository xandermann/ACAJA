package wrapper;

import java.io.*;
import java.util.Iterator;
import javax.swing.JOptionPane;

public abstract class StreamIterator implements Iterator<String>{
	private BufferedReader streamToRead;
	private String lastLineRead;
	
	private void readNextLine() {
		try {
			/**
			 * ETUDE DE LA LIGNE SUIVANTE.
			 * 
			 * On lit la ligne suivante, et on ferme le flux 
			 * si il n'y a pas de ligne suivante.
			 */
			if((lastLineRead = streamToRead.readLine()) == null) streamToRead.close();
		} catch (IOException e) {
			
			/**
			 * EXCEPTIONS, PAS DE FLUX TROUVES. 
			 */
			JOptionPane.showMessageDialog(null, MessageConstants.FALIURE_UNFINDABLE_STREAMS);
		}
	}
	
	public StreamIterator(InputStream stream) {
		streamToRead = new BufferedReader(new InputStreamReader(stream));
		readNextLine();
	}
	
	@Override
	public boolean hasNext() {
		return lastLineRead != null;
	}

	@Override
	public String next() {
		String oldLineRead = lastLineRead;
		readNextLine();
		return oldLineRead;
	}
}
