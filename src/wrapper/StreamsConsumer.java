package wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class StreamsConsumer{
	//=======================================================================================================================
	//=======================================================================================================================	
	
	
	public static void consumeStreams(Process processtoBeConsume) {
		try {
			BufferedReader consumer = new BufferedReader(new InputStreamReader(processtoBeConsume.getErrorStream()));
			String line = "";
			try {
				while((line = consumer.readLine()) != null);
			} finally {
				consumer.close();
			}
			
			consumer = new BufferedReader(new InputStreamReader(processtoBeConsume.getInputStream()));
			try {
				while((line = consumer.readLine()) != null);
			} finally {
				consumer.close();
			}
		} catch(IOException ioe) {}
		
		 try {
			 processtoBeConsume.waitFor();
		} catch (InterruptedException e) {}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================	
}
