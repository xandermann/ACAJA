package ffmpeg_tools;

import java.io.File;
import java.io.IOException;

public abstract class FFmpegRuntime {
	protected static String PATH_FFMPEG;
	protected final static Runtime RUN = Runtime.getRuntime();
	
	private void install() throws IOException {
		if(System.getProperty("os.name").contains("Linux")) {
			RUN.exec("chmod u+x ../../scripts/intall_ffmpeg_linux.sh && ../../scripts/intall_ffmpeg_linux.sh");
			PATH_FFMPEG = "ffmpeg";
		}else
			PATH_FFMPEG = "../../ffmpeg/bin/ffmpeg.exe";
	}
	
	public void execute(String ffmpegRequest) throws IOException {
		if(PATH_FFMPEG==null) this.install();
		RUN.exec(PATH_FFMPEG+ffmpegRequest);
	}
}
