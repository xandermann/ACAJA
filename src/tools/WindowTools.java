package tools;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public final class WindowTools {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public static void showLogo(JFrame f) {
		try {
			f.setIconImage(ImageIO.read(Resources.ACAJA_LOGO));
		} catch (IOException ioe) {}
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
	
	
	public static void showWindow(JFrame window) {
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		        window.setVisible(true);
		    }
		});
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
