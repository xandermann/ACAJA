package gui.conversion.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import javax.swing.*;

import files.enumerations.SettingType;
import gui.conversion.model.ConversionModel;

public final class SummaryView extends JPanel implements Observer {
	//=======================================================================================================================
	//=======================================================================================================================

	
	private ConversionModel model;
	private JLabel inputFileLabel, videoLabel, soundLabel, durationLabel;
	private JTextField outputFileText;

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public SummaryView(ConversionModel model) {
		this.model = model;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		JPanel inputFilePanel = new JPanel();
		inputFilePanel.setLayout(new FlowLayout());
		inputFilePanel.add(new JLabel("Fichier selectionne : "), BorderLayout.EAST);
		inputFilePanel.add(inputFileLabel = new JLabel("NA"), BorderLayout.WEST);
		
		
		JPanel videoPanel = new JPanel();
		videoPanel.setLayout(new FlowLayout());
		videoPanel.add(new JLabel("Codec video actuel : "), BorderLayout.EAST);
		videoPanel.add(videoLabel = new JLabel("NA"), BorderLayout.WEST);
		
		
		JPanel audioPanel = new JPanel();
		audioPanel.setLayout(new FlowLayout());
		audioPanel.add(new JLabel("Codec audio actuel : "), BorderLayout.EAST);
		audioPanel.add(soundLabel = new JLabel("NA"), BorderLayout.WEST);
		
		
		JPanel durationLabelPanel = new JPanel();
		durationLabelPanel.setLayout(new FlowLayout());
		durationLabelPanel.add(new JLabel("Duree : "), BorderLayout.EAST);
		durationLabelPanel.add(durationLabel = new JLabel("NA"),BorderLayout.WEST);

		
		JPanel outputFilePanel = new JPanel();
		outputFilePanel.setLayout(new FlowLayout());
		outputFilePanel.add(new JLabel("Fichier de destination : "), BorderLayout.EAST);
		outputFilePanel.add(outputFileText = new JTextField("NA"),BorderLayout.WEST);
		outputFileText.setPreferredSize(new Dimension(100, 20));
		outputFileText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) model.setDestination(outputFileText.getText());
			}
		});
		
		
		add(inputFilePanel);
		add(videoPanel);
		add(audioPanel);
		add(durationLabelPanel);
		add(outputFilePanel);
		
		
		setSize(new Dimension(300, 100));
	}

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	@Override
	public void update(Observable o, Object arg) {
		if (model.getCurrentFile() != null) {
			inputFileLabel.setText(model.getCurrentFile().getSourceFileName());
			videoLabel.setText(model.getCurrentFile().getSettings().get(SettingType.VIDEO_CODEC));
			soundLabel.setText(model.getCurrentFile().getSettings().get(SettingType.AUDIO_CODEC));
			durationLabel.setText(this.model.getCurrentFile().getDuration());
			outputFileText.setText(this.model.getCurrentFile().getDestinationFileName());
		} else {
			inputFileLabel.setText("NA");
			videoLabel.setText("NA");
			soundLabel.setText("NA");
			durationLabel.setText("NA");
			outputFileText.setText("NA");
		}
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
