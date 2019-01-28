package tools;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public final class Tools {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public static void showLogo(JFrame f) {
		try {
			f.setIconImage(ImageIO.read(new File(ResourcesPaths.ACAJA_LOGO_PATH)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public static void focusWindow(JFrame window) {
		//recuperation des dimensions de l'ecran.
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		//on positionne la fenetre au centre de l'ecran.
		window.setLocation(
				(int)(screen.getWidth()-window.getWidth())/2, 
				(int)(screen.getHeight()-window.getHeight())/2);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
