package gui.conversion.views_controllers;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import files.enumerations.SettingType;
import files.files.SettingsFile;
import gui.conversion.model.ConversionModel;
import gui.style.StylizedJPanel;
import wrapper.language.CodecConstants;

public final class SoundSettingsView extends SettingsView{
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	private JComboBox<String> codecsComboBox;
	private JTextField bitrateText, samplingRateText, channelsText;

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	public SoundSettingsView(ConversionModel model) {
		super(model);


		StylizedJPanel codecPanel = new StylizedJPanel();
		codecPanel.add(new JLabel("Codec audio : "), BorderLayout.WEST);
		codecsComboBox = new JComboBox<String>(CodecConstants.ALL_SUPPORTED_AUDIO_CODECS);
		codecPanel.add(codecsComboBox, BorderLayout.EAST);
		codecsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true)
						model.modify(SettingType.AUDIO_CODEC, ((JComboBox) e.getSource()).getSelectedItem().toString());
				}
			}
		});

		
		StylizedJPanel bitratePanel = new StylizedJPanel();
		bitratePanel.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrateText = new JTextField();
		bitrateText.setPreferredSize(new Dimension(100, 20));
		bitratePanel.add(bitrateText, BorderLayout.EAST);
		bitrateText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.AUDIO_BITRATE, bitrateText.getText());
				}
			}
		});

		
		StylizedJPanel samplingRatePanel = new StylizedJPanel();
		samplingRatePanel.add(new JLabel("Taux d'echantillonnage (Hz) : "), BorderLayout.WEST);
		samplingRateText = new JTextField();
		samplingRateText.setPreferredSize(new Dimension(100, 20));
		samplingRatePanel.add(samplingRateText, BorderLayout.EAST);
		samplingRateText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.SAMPLING_RATE, samplingRateText.getText());
				}
			}
		});
		

		StylizedJPanel channelsPanel = new StylizedJPanel();
		channelsPanel.add(new JLabel("Nombre de canaux audio en sortie : "), BorderLayout.WEST);
		channelsText = new JTextField();
		channelsText.setPreferredSize(new Dimension(100, 20));
		channelsPanel.add(channelsText, BorderLayout.EAST);
		channelsText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.NUMBER_AUDIO_CHANNELS, channelsText.getText());
				}
			}
		});
		
		
		add(codecPanel);
		add(bitratePanel);
		add(samplingRatePanel);
		add(channelsPanel);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	@Override
	public void update(Observable o, Object arg) {
		isChange = false;
		if(model.getCurrentFile() != null){
			SettingsFile settings = model.getCurrentFile();
			codecsComboBox.setSelectedItem(settings.recent(SettingType.AUDIO_CODEC));
			bitrateText.setText(settings.recent(SettingType.AUDIO_BITRATE));
			samplingRateText.setText(settings.recent(SettingType.SAMPLING_RATE));
			channelsText.setText(settings.recent(SettingType.NUMBER_AUDIO_CHANNELS));
		}else{
			codecsComboBox.setSelectedIndex(0);
			bitrateText.setText("");
			samplingRateText.setText("");
			channelsText.setText("");
		}
		isChange = true;
	}
	
	

	//=======================================================================================================================
	//=======================================================================================================================
}
