package gui_conversion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VideoSettingsPanel extends JPanel{
	
	private ConversionModel model;
	
	public VideoSettingsPanel(ConversionModel m) {
		this.model = m;
	}
	
	private void reevaluatePanel() {
		this.removeAll();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5, 2));
		p.add(new JLabel("Codec utilisé : "));
		p.add(new JComboBox<>());
		p.add(new JLabel("Résolution : "));
		p.add(new JTextField());
		p.add(new JLabel("Bitrate (kb/s : "));
		p.add(new JTextField());
		p.add(new JLabel("Images par seconde (FPS) : "));
		p.add(new JTextField());
		p.add(new JLabel("Fichiers sous-titres : "));
		p.add(new JFileChooser("Parcourir"));
	}
}
