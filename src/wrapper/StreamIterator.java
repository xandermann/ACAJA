package wrapper;

import java.io.*;
import java.util.Iterator;
import javax.swing.JOptionPane;

public abstract class StreamIterator implements Iterator<String>{
	private BufferedReader streamToRead;
	private String lastLineRead;
	
	public StreamIterator(InputStream stream) {
		streamToRead = new BufferedReader(new InputStreamReader(stream));
	}
	
	@Override
	public boolean hasNext() {
		try {
			/**
			 * ETUDE DE LA LIGNE ULTERIEURE.
			 * 
			 * On lit la ligne ulterirure, et on ferme le flux 
			 * si il n'y a pas de ligne ulterieur.
			 */
			if((lastLineRead = streamToRead.readLine()) == null) streamToRead.close();
			return lastLineRead == null;
			
		} catch (IOException e) {
			
			/**
			 * EXCEPTIONS, PAS DE FLUX TROUVES. 
			 */
			JOptionPane.showMessageDialog(null, MessageConstants.FALIURE_UNFINDABLE_STREAMS);
			return false;
		}
	}

	@Override
	public String next() {
		return lastLineRead;
	}
}
