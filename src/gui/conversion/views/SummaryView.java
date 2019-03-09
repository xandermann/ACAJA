package gui.conversion.views;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;

import files.enumerations.SettingType;
import gui.conversion.model.ConversionModel;
import gui.style.StyleTheme;
import gui.style.StylizedJPanel;

public final class SummaryView extends StylizedJPanel implements Observer {
	//=======================================================================================================================
	//=======================================================================================================================

	
	private ConversionModel model;
	private JLabel inputFileLabel, videoLabel, soundLabel, durationLabel;
	private JTextField outputFileText;

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public SummaryView(ConversionModel model) {
		super();
		if((this.model = model) == null) throw new NullPointerException("ConversionModel null !");
		setSize(new Dimension(300, 100));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		StylizedJPanel inputFilePanel = new StylizedJPanel();
		inputFilePanel.setBackground(Color.LIGHT_GRAY);
		inputFilePanel.add(new JLabel("Fichier selectionne : "), BorderLayout.EAST);
		inputFilePanel.add(inputFileLabel = new JLabel("NA"), BorderLayout.WEST);
		
		
		StylizedJPanel videoPanel = new StylizedJPanel();
		videoPanel.setBackground(Color.LIGHT_GRAY);
		videoPanel.add(new JLabel("Codec video actuel : "), BorderLayout.EAST);
		videoPanel.add(videoLabel = new JLabel("NA"), BorderLayout.WEST);
		
		
		StylizedJPanel audioPanel = new StylizedJPanel();
		audioPanel.setBackground(Color.LIGHT_GRAY);
		audioPanel.add(new JLabel("Codec audio actuel : "), BorderLayout.EAST);
		audioPanel.add(soundLabel = new JLabel("NA"), BorderLayout.WEST);
		
		
		StylizedJPanel durationLabelPanel = new StylizedJPanel();
		durationLabelPanel.setBackground(Color.LIGHT_GRAY);
		durationLabelPanel.add(new JLabel("Duree : "), BorderLayout.EAST);
		durationLabelPanel.add(durationLabel = new JLabel("NA"),BorderLayout.WEST);

		
		StylizedJPanel outputFilePanel = new StylizedJPanel();
		outputFilePanel.setBackground(Color.LIGHT_GRAY);
		outputFilePanel.add(new JLabel("Fichier de destination : "), BorderLayout.EAST);
		outputFilePanel.add(outputFileText = new JTextField(""),BorderLayout.WEST);
		outputFileText.setPreferredSize(new Dimension(100, 20));
		outputFileText.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(model.getCurrentFile() != null) model.setDestination(outputFileText.getText());
			}
		});
		
		
		add(inputFilePanel);
		add(videoPanel);
		add(audioPanel);
		add(durationLabelPanel);
		add(outputFilePanel);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	@Override
	public void update(Observable o, Object arg) {
		if (model.getCurrentFile() != null) {
			inputFileLabel.setText(model.getCurrentFile().getSourceFileName());
			videoLabel.setText(model.getCurrentFile().getSettings().get(SettingType.VIDEO_CODEC));
			soundLabel.setText(model.getCurrentFile().getSettings().get(SettingType.AUDIO_CODEC));
			durationLabel.setText(model.getCurrentFile().getDuration());
			if(model.getCurrentFile().getDestinationFileName() == "")
				outputFileText.setText(model.getCurrentFile().getSourceFileNameWithoutExtension());
			else
				outputFileText.setText(model.getCurrentFile().getDestinationFileName());
		} else {
			inputFileLabel.setText("NA");
			videoLabel.setText("NA");
			soundLabel.setText("NA");
			durationLabel.setText("NA");
			outputFileText.setText("");
		}
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
