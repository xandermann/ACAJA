package gui_conversion;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChoose {
	
	public static JFileChooser jfc;
	
	public static JButton FileChoose() {
		
		jfc = new JFileChooser("Parcourir");
	
		JButton parcourir = new JButton("Parcourir");
		
		parcourir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jfc.showOpenDialog(parcourir);
			}});

		
		return parcourir;
		
	}
}
