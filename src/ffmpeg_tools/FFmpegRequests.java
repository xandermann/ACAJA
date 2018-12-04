package ffmpeg_tools;
import java.io.IOException;
import tools.Tools;

public class FFmpegRequests extends FFmpegBasic{
	private String sourcePath, destinationPath;
	
	
	public FFmpegRequests(String sourcePath, String destinationPath) {
		if(sourcePath==null) 
			throw new NullPointerException("Le chemin vers le fichier sourcePath est null !");
		else if(sourcePath.equals(""))
				throw new NullPointerException("Le chemin vers le fichier sourcePath est vide !");
			else if(destinationPath==null)
					throw new NullPointerException("Le chemin vers le fichier destinationPath est null !");
				else if(destinationPath.equals(""))
						throw new NullPointerException("Le chemin vers le fichier destinationPath est vide !");				
		this.sourcePath = sourcePath;
		this.destinationPath = destinationPath;
	}
	
	
	public void convertTo() {
		execute(ffmpegPath+" -i "+sourcePath+" "+destinationPath);
	}

	
	public void crop(int width, int height, int leftCornerX, int leftCornerY) {
		execute(ffmpegPath+" -i "+sourcePath+
				" -filter:v crop=\""+width+":"+height+":"+leftCornerX+":"+leftCornerY+"\" "+
				destinationPath);
	}
	
	
	public void cut(int timeStart, int timeEnd) {
		execute(ffmpegPath+" -i "+sourcePath+
				" -ss "+Tools.convertTimeToString(timeStart)+" -t "+Tools.convertTimeToString(timeEnd)+" -c:v copy -c:a copy "
				+destinationPath);
	}
}
