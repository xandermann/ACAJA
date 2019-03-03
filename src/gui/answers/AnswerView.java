package gui.answers;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import resources.TimeTools;

public final class AnswerView extends JPanel{
	public AnswerView(String support) {
		if(support == null) throw new NullPointerException("Support null !");
		
		
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
		name.setSize(new Dimension(500, 750));
		area.add(new JScrollPane(text));
		
		
		add(name, BorderLayout.NORTH);
		add(area, BorderLayout.CENTER);
	}
}
