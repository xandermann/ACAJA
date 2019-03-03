package gui.answers;

import java.awt.*;
import javax.swing.*;
import gui.WindowTools;

public final class AnswersWindow extends JFrame{
	public AnswersWindow(){
		super("Historique des reponses de FFmpeg.");
		setResizable(false);
		AnswersView av = new AnswersView();
		setContentPane(av);
		setSize(new Dimension(av.getWidth(), av.getHeight()));
		setLocationRelativeTo(null);
		WindowTools.executeWindow(this);
	}
}
