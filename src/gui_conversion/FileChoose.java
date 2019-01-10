package gui_conversion;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChoose {
	
	private static JFileChooser jfc;
	
	public static File FileChoose() {
		
		jfc = new JFileChooser("Parcourir");
		jfc.showOpenDialog(null);
/*
 * 		JButton parcourir = new JButton("Parcourir");
		
		parcourir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jfc.showOpenDialog(parcourir);
			}});
 */

		
		return jfc.getSelectedFile();
		
	}
	
}
