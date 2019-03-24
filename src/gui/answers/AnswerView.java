package gui.answers;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import resources.TimeTools;
import wrapper.streams.managers.filters.Errors;

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
	public AnswerView(File support) {
		super(new BorderLayout());
		if(support == null) throw new NullPointerException("Support null !");
		
		
		/**
		 * CONTENU DU FICHIER. ----------------------------------------------------------------------------------
		 */
		JEditorPane text = new JEditorPane();
		text.setContentType("text/html");
		text.setEditable(false);
		
		String content = "";
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(support));
			String line = null;
			
			while((line=reader.readLine()) != null) 
				content += "<span" + 
								(!Errors.track(line) ? " class=error" : 
									(line.contains("Input") || line.contains("Output") ? " class=io" : 
										(line.contains("configuration") ? " class=config" : 
											(line.contains("lib") ? " class=lib" : 
												(line.contains("frame=") ? " class=conversion" : 
													(line.contains("Stream #") || line.contains("Duration") ? " class=metadata" :
														(line.contains("ffmpeg") ||line.contains("GCC") ) ? " class=start" : 
															")"))))))+">" +
									getDown(line) +
							"</span>" +
							"<br>";
			
			
			reader.close();
		} catch (Exception e) {} 
		
		
		/**
		 * DISPOSITION ET COLORISATION DU CODE.  ---------------------------------------------------------------------
		 */
		String start = "<html>" + 
							"<head>" +
								"<style>" +
									"span {" +
										"white-space: nowrap;" +
									"}" +
									".start {" +
										"background-color: black;" +
									 "}" +
									".io {" +
										"background-color: blue;" +
								     "}" +
									 ".error {" +
										"background-color: red;" +
								     "}" +
									 ".config {" +
										"background-color: gray;" +
								     "}" +
									 ".lib {" +
										"background-color: yellow;" +
								     "}" +
									 ".conversion {" +
										"background-color: green;" +
								     "}" +
									 ".metadata {" +
										"background-color: #77b5fe;" +
								     "}" +
									 ".lib, .metadata {" +
										"color: black;" +
								     "}" +
									 ".io, .error, .config, .conversion, .start {" +
										"color: white;" +
								     "}" +
							   "</style>" +
							"</head>" +
							"<body>";
		String end = 		"</body>" +
					"</html>";
		text.setText(start+content+end);
		
		
		/**
		 * DISPOSITION DES COMPOSANTS. --------------------------------------------------------------------------------
		 */
		setSize(new Dimension(500, 500));
		JPanel name = new JPanel(new BorderLayout());
		name.setPreferredSize(new Dimension(500, 30));
		
		String n = support.getName();
		name.add(new JLabel(
				"Rapport du "+n.substring(n.indexOf("_")+1, n.lastIndexOf("_")) + " a " +
				TimeTools.millisToTime(Long.parseLong(n.substring(n.lastIndexOf("_")+1, n.indexOf(".")))), 
				JLabel.CENTER));
		JPanel area = new JPanel(new BorderLayout());
		name.setSize(new Dimension(500, 450));
		area.add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		add(name, BorderLayout.NORTH);
		add(area, BorderLayout.CENTER);
	}
	
	
	
	/**
	 * [ FORMATER UNE LIGNE. ]
	 * 
	 * @param line		La ligne a formater.
	 * @return			la ligne formatee.
	 */
	private String getDown(String line) {
		return line.length()<=80 ? line : (line.substring(0, 80)+"<br>"+getDown(line.substring(80, line.length())));
	}
}
