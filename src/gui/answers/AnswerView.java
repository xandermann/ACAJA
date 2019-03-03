package gui.answers;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import resources.TimeTools;

/**
 * [ CLASSE VUE D'UNE REPONSE. ]
 * 
 * Cette vue affiche le contenu d'un fichier de sauvegarde d'un flux. 
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class AnswerView extends JPanel{
	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public AnswerView(String support) {
		if(support == null) throw new NullPointerException("Support null !");
		
		
		/**
		 * CONTENU DU FICHIER.
		 */
		JTextArea text = new JTextArea();
		text.setEditable(false);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(support)));
			String line = null;
			if((line=reader.readLine()) != null) text.setText(line);
			while((line=reader.readLine()) != null) text.setText(text.getText()+"\n"+line);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
		
		
		/**
		 * DISPOSITION DES COMPOSANTS.
		 */
		setSize(new Dimension(500, 500));
		
		setLayout(new BorderLayout());
		JPanel name = new JPanel(new BorderLayout());
		name.setSize(new Dimension(500, 50));
		
		String n = (new File(support)).getName();
		name.add(new JLabel(
				"Rapport du "+n.substring(n.indexOf("_")+1, n.lastIndexOf("_")) + " a " +
				TimeTools.millisToTime(Long.parseLong(n.substring(n.lastIndexOf("_")+1, n.indexOf(".")))), 
				JLabel.CENTER));
		JPanel area = new JPanel(new BorderLayout());
		name.setSize(new Dimension(500, 450));
		area.add(new JScrollPane(text));
		
		add(name, BorderLayout.NORTH);
		add(area, BorderLayout.CENTER);
	}
}
