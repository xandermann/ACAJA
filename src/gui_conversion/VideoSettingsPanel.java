package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VideoSettingsPanel extends JPanel{
	
	private ConversionModel model;
	
	public VideoSettingsPanel(ConversionModel m) {
		this.model = m;
		this.reevaluatePanel();
	}
	
	private void reevaluatePanel() {
		this.setSize(new Dimension(400, 400));
		
		this.setLayout(new GridLayout(5,1));
		
		JPanel Codec = new JPanel();
		Codec.setLayout(new BorderLayout());
		Codec.add(new JLabel("Codec utilisé : "),BorderLayout.NORTH);
		Codec.add(new JComboBox<>(),BorderLayout.CENTER);
		
		JPanel reso = new JPanel();
		reso.setLayout(new BorderLayout());
		reso.add(new JLabel("Résolution : "),BorderLayout.NORTH);
		reso.add(new JTextField(),BorderLayout.CENTER);
		
		JPanel br = new JPanel();
		br.setLayout(new BorderLayout());
		br.add(new JLabel("Bitrate (kb/s : "),BorderLayout.NORTH);
		br.add(new JTextField(),BorderLayout.CENTER);
		
		JPanel panef = new JPanel();
		panef.setLayout(new BorderLayout());
		panef.add(new JLabel("Images par seconde (FPS) : "),BorderLayout.NORTH);
		panef.add(new JTextField(),BorderLayout.CENTER);
		
		JPanel panest = new JPanel();
		panest.setLayout(new BorderLayout());
		panest.add(new JLabel("Fichiers sous-titres : "),BorderLayout.NORTH);
		//panest.add(new JFileChooser("Parcourir"),BorderLayout.CENTER);
		
		JButton parcourir = FileChoose.FileChoose();
		panest.add(parcourir,BorderLayout.CENTER);
		//////////////////////////////////TO DO     FileChoose.jfc.getSelectedFile();
		
		
		
		this.add(Codec);
		this.add(reso);
		this.add(br);
		this.add(panef);
		this.add(panest);
		//this.repaint();
	}
	
}
