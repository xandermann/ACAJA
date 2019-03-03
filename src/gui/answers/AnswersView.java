package gui.answers;

import java.io.File;
import java.util.*;
import java.util.List;
import javax.swing.*;
import gui.WindowTools;
import java.awt.*;
import java.awt.event.*;
import resources.*;

public final class AnswersView extends JPanel {
	public AnswersView() {
		File[] filesErr =  ResourceConstants.STDERR_ANSWERS.listFiles();
		File[] filesOut = ResourceConstants.STDOUT_ANSWERS.listFiles();
		List<File> files = new ArrayList<File>();
		for(File f : filesErr) files.add(f);
		for(File f : filesOut) files.add(f);
		files.sort(new Comparator<File>(){
			public int compare(File f1, File f2) {
				String timeMillis1 = f1.getName().split("_")[f1.getName().split("_").length-1];
				timeMillis1 = timeMillis1.substring(0, timeMillis1.indexOf("."));
				String timeMillis2 = f2.getName().split("_")[f2.getName().split("_").length-1];
				timeMillis2 = timeMillis2.substring(0, timeMillis2.indexOf("."));
				return (int) (Long.parseLong(timeMillis2) - Long.parseLong(timeMillis1));
			}
		});
		
		
		setLayout(new GridLayout(files.size(), 1));
		setSize(new Dimension(400, (30*files.size())<500 ? (30*files.size()) : 500));
		
		for(File f : files) {
			String n = f.getName();            
			JButton j = new JButton(
					"Rapport du "+n.substring(n.indexOf("_")+1, n.lastIndexOf("_")) + " a " +
					TimeTools.millisToTime(Long.parseLong(n.substring(n.lastIndexOf("_")+1, n.indexOf(".")))));
			j.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame window = new JFrame("Affichage de la reponse : "+f.getName()+".");
					window.setResizable(false);
					AnswerView av = new AnswerView(f.getAbsolutePath());
					window.setContentPane(av);
					window.setSize(new Dimension(av.getWidth(), av.getHeight()));
					window.setLocationRelativeTo(null);
					WindowTools.executeWindow(window);
				}
			});
			add(j);
		}
		
		
		new JScrollPane(this);
	}
}
