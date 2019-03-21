package gui.conversion.views;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import files.files.SettingsFile;
import files.enumerations.SettingType;
import gui.conversion.ConversionContext;
import gui.conversion.ConversionModel;
import gui.style.StyleTheme;
import gui.style.StylizedJPanel;

public final class SummaryView extends StylizedJPanel implements Observer {
	//=======================================================================================================================
	//=======================================================================================================================

	
	private JLabel inputFileLabel, videoLabel, soundLabel, durationLabel;
	private JTextField outputFileText;

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public SummaryView() {
		super();
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
				if(ConversionContext.$M.getCurrentFile() != null) 
					((ConversionModel) ConversionContext.$M).setDestination(outputFileText.getText());
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
		if (ConversionContext.$M.getCurrentFile() != null) {
			inputFileLabel.setText(ConversionContext.$M.getCurrentFile().getSourceFileName());
			videoLabel.setText(((SettingsFile) ConversionContext.$M.getCurrentFile()).getSettings().get(SettingType.VIDEO_CODEC));
			soundLabel.setText(((SettingsFile) ConversionContext.$M.getCurrentFile()).getSettings().get(SettingType.AUDIO_CODEC));
			durationLabel.setText(ConversionContext.$M.getCurrentFile().getDuration());
			if(ConversionContext.$M.getCurrentFile().getDestinationFileName() == "")
				outputFileText.setText(ConversionContext.$M.getCurrentFile().getSourceFileNameWithoutExtension());
			else
				outputFileText.setText(ConversionContext.$M.getCurrentFile().getDestinationFileName());
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
