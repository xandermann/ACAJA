package gui.answers;

import java.io.File;
import java.util.*;
import java.util.List;
import javax.swing.*;
import gui.WindowTools;
import java.awt.*;
import java.awt.event.*;
import resources.*;

/**
 * [ CLASSE VUE DES REPONSES. ]
 * 
 * Cette classe affiche dans un GridLayout les fichiers de 
 * sauvegarde des flux.
 * 
 * Les fichiers affiches dans l'ordre du plus recent au plus ancien.
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class AnswersView extends JPanel {
	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public AnswersView() {
		/**
		 * REUNION DES FICHIERS.
		 */
		File[] filesErr =  ResourceConstants.STDERR_ANSWERS.listFiles();
		File[] filesOut = ResourceConstants.STDOUT_ANSWERS.listFiles();
		List<File> files = new ArrayList<File>();
		for(File f : filesErr) files.add(f);
		for(File f : filesOut) files.add(f);
		
	
		/**
		 * TRI DU PLUS RECENT AU PLUS ANCIEN.
		 */
		files.sort(new Comparator<File>(){
			public int compare(File f1, File f2) {
				String timeMillis1 = f1.getName().split("_")[f1.getName().split("_").length-1];
				timeMillis1 = timeMillis1.substring(0, timeMillis1.indexOf("."));
				String timeMillis2 = f2.getName().split("_")[f2.getName().split("_").length-1];
				timeMillis2 = timeMillis2.substring(0, timeMillis2.indexOf("."));
				return (int) (Long.parseLong(timeMillis2) - Long.parseLong(timeMillis1));
			}
		});
		
		
		/**
		 * DISPOSITION.
		 */
		setLayout(new BorderLayout());
		setSize(new Dimension(400, 200));
		JPanel main = new JPanel(new GridLayout(files.size(), 1));
		main.setSize(new Dimension(400, 200));
		for(File f : files) {
			String n = f.getName();            
			JButton j = new JButton(
					"Rapport du "+n.substring(n.indexOf("_")+1, n.lastIndexOf("_")) + " a " +
					TimeTools.millisToTime(Long.parseLong(n.substring(n.lastIndexOf("_")+1, n.indexOf(".")))));
			j.setSize(new Dimension(400, 30));
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
			main.add(j);
		}		
		add(new JScrollPane(main, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
	}
}
