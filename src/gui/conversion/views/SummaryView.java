package gui.conversion.views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import files.files.SettingsFile;
import files.enumerations.SettingType;
import gui.general.Context;
import gui.style.StylizedJPanel;
import gui.conversion.ConversionModel;

public final class SummaryView extends StylizedJPanel implements Observer {
	//=======================================================================================================================
	//=======================================================================================================================

	
	private TwoTextsView inputFileLabel, videoLabel, soundLabel, durationLabel;
	private JTextField outputFileText;

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public SummaryView() {
		super();
		
		Context.$C(2, this);
		
		setSize(new Dimension(320, 400));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		StylizedJPanel inputFilePanel = new StylizedJPanel();
		inputFilePanel.setBackground(Color.LIGHT_GRAY);
		inputFilePanel.setToolTipText("Le nom du fichier en cours d'utilisation.");
		inputFilePanel.add(inputFileLabel = new TwoTextsView("Fichier selectionne :", 8, "NA", 12));
		
		
		StylizedJPanel videoPanel = new StylizedJPanel();
		videoPanel.setBackground(Color.LIGHT_GRAY);
		videoPanel.setToolTipText("Le codec video du fichier en cours d'utilisation.");
		videoPanel.add(videoLabel = new TwoTextsView("Codec video actuel : ", 8, "NA", 12));
		
		
		StylizedJPanel audioPanel = new StylizedJPanel();
		audioPanel.setBackground(Color.LIGHT_GRAY);
		audioPanel.setToolTipText("Le codec audio du fichier en cours d'utilisation.");
		audioPanel.add(soundLabel = new TwoTextsView("Codec audio actuel : ", 8, "NA", 12));
		
		
		StylizedJPanel durationPanel = new StylizedJPanel();
		durationPanel.setBackground(Color.LIGHT_GRAY);
		durationPanel.setToolTipText("La duree du fichier en cours d'utilisation.");
		durationPanel.add(durationLabel = new TwoTextsView("Duree : ", 8, "NA", 12));

		
		StylizedJPanel outputFilePanel = new StylizedJPanel();
		outputFilePanel.setBackground(Color.LIGHT_GRAY);
		outputFilePanel.setToolTipText("La nom du fichier de sortie.");
		outputFilePanel.add(new JLabel("Fichier de destination : "), BorderLayout.EAST);
		outputFilePanel.add(outputFileText = new JTextField(""),BorderLayout.WEST);
		outputFileText.setPreferredSize(new Dimension(100, 20));
		outputFileText.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					Context.$W.requestFocus();
				else {
					if(Context.$M.getCurrentFile() != null) 
						((ConversionModel) Context.$M).setDestination(outputFileText.getText());
				}
			}
		});
		
		
		add(inputFilePanel);
		add(videoPanel);
		add(audioPanel);
		add(durationPanel);
		add(outputFilePanel);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	@Override
	public void update(Observable o, Object arg) {
		if (Context.$M.getCurrentFile() != null) {
			inputFileLabel.$(Context.$M.getCurrentFile().getSourceFileName());
			videoLabel.$(((SettingsFile) Context.$M.getCurrentFile()).getSettings().get(SettingType.VIDEO_CODEC));
			soundLabel.$(((SettingsFile) Context.$M.getCurrentFile()).getSettings().get(SettingType.AUDIO_CODEC));
			durationLabel.$(Context.$M.getCurrentFile().getDuration());
			if(Context.$M.getCurrentFile().getDestinationFileName() == "")
				outputFileText.setText(Context.$M.getCurrentFile().getSourceFileNameWithoutExtension());
			else
				outputFileText.setText(Context.$M.getCurrentFile().getDestinationFileName());
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
