package tools;
import java.io.File;
import ffmpeg_tools.FFmpegBasic;

public class TemporaryDataManager {
	public static void clear() {
		 File directory = new File(FFmpegBasic.temporaryDataPath);
		 if(verify(directory))
			 for(File f : directory.listFiles())  f.delete();
	}
	
	private static boolean verify(File f) {
		return f.exists() && f.isDirectory();
	}
	
	public static void validate() throws DataDirectoryAbsentException  {
		File directory = new File(FFmpegBasic.temporaryDataPath);
		if(!verify(directory)) 
			if(!directory.mkdir()) throw new DataDirectoryAbsentException();
	}
}
