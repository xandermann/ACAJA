package ffmpeg_tools;

import java.io.File;

public class SystemRequests {

	/**
	 * TODO
	 */
	public final String PATH_TEMPORARY_FILES = "";

	/**
	 * TODO
	 */
	public final int[] LIBRARY_THUMBAIL_RESOLUTION = new int[] {};

	/**
	 * TODO
	 */
	public final int[] VIDEO_TIMELINE_THUMBAIL_RESOLUTION = new int[] {};

	/**
	 * 
	 */
	public final int[] SOUND_TIMELINE_THUMBAIL_RESOLUTION = new int[] {};

	/**
	 * TODO
	 */
	public final int[] VIDEO_RESOLUTION = new int[] {};

	/**
	 * TODO
	 */
	public SystemRequests() {
	}

	/**
	 * TODO
	 * 
	 * @param file
	 * @param l
	 * @return
	 */
	public File createVideoFrame(File file, long l) {
		return file;
	}

	/**
	 * TODO
	 * 
	 * @param file
	 * @param tab
	 * @return
	 */
	public File createThumbail(File file, int[] tab) {
		return file;
	}

	/**
	 * TODO
	 * 
	 * @param file
	 * @param l
	 * @return
	 */
	private File extractImage(File file, long l) {
		return file;
	}

	/**
	 * TODO
	 * 
	 * @param file
	 * @param tab
	 * @return
	 */
	private int[] resizeImage(File file, int[] tab) {
		return tab;
	}

	/**
	 * TODO
	 * 
	 * @param file
	 * @param tab
	 * @return
	 */
	private int[] findGoodResolution(File file, int[] tab) {
		return tab;
	}

	/**
	 * TODO
	 * 
	 * @param file
	 * @param l
	 * @return
	 */
	public long getDuration(File file, long l) {
		return l;
	}

	/**
	 * TODO
	 * @param file
	 * @param s
	 * @return
	 */
	public long getDuration(File file, String s) {
		return 0;
	}

	/**
	 * TODO
	 * @param file
	 * @return
	 */
	public Object[] getSettings(File file) {
		return null;
	}

}
