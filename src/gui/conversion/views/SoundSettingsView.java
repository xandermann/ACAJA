package gui.conversion.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import files.enumerations.SettingType;
import files.files.SettingsFile;
import gui.general.Context;
import gui.style.StylizedJPanel;
import wrapper.language.CodecConstants;

public final class SoundSettingsView extends SettingsView{
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	private JComboBox<String> codecsComboBox;
	private JTextField bitrateText, samplingRateText, channelsText;

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public void updateAudioCodecs() {
		if(isChange == true) {
			String videoFormat = ((SettingsFile) Context.$M.getCurrentFile()).recent(SettingType.VIDEO_FORMAT);
			String codecFormat = ((SettingsFile) Context.$M.getCurrentFile()).recent(SettingType.VIDEO_CODEC);
			if(videoFormat != null && codecFormat != null) {
				Map<String,List<String>> codecs = CodecConstants.CORRESPONDING_EXTENSION.get(videoFormat);
				List<String> codecsAudio = new ArrayList<String>();
				codecsAudio = codecs.get(codecFormat);
				if(codecsAudio != null) {
					String[] codecsArray = Arrays.copyOf(codecsAudio.toArray(), codecsAudio.size(), String[].class);
					codecsComboBox.removeAllItems();
					codecsComboBox.setModel(new DefaultComboBoxModel<String>(codecsArray));
					codecsComboBox.setSelectedIndex(0);
				}
			}
		}
	}
	
	
	public SoundSettingsView() {
		Context.$C(5, this);
		
		StylizedJPanel codecPanel = new StylizedJPanel();
		codecPanel.add(new JLabel("Codec audio : "), BorderLayout.WEST);
		codecsComboBox = new JComboBox<String>(CodecConstants.ALL_SUPPORTED_AUDIO_CODECS);
		codecPanel.setToolTipText("Le codec audio du fichier de sortie.");
		codecPanel.add(codecsComboBox, BorderLayout.EAST);
		codecsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Context.$M.getCurrentFile() != null) {
					if(isChange == true && ((JComboBox<?>) e.getSource()).getSelectedItem() != null)
						 Context.$M.modify(SettingType.AUDIO_CODEC, 
								 ((JComboBox<?>) e.getSource()).getSelectedItem().toString());
				}
			}
		});

		
		StylizedJPanel bitratePanel = new StylizedJPanel();
		bitratePanel.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrateText = new JTextField();
		bitrateText.setPreferredSize(new Dimension(100, 20));
		bitratePanel.setToolTipText("Le taux de bits par seconde de la partie audio du fichier en cours d'utilisation.");
		bitratePanel.add(bitrateText, BorderLayout.EAST);
		bitrateText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					Context.$W.requestFocus();
				else {
					if(Context.$M.getCurrentFile() != null) {
						if(isChange == true) 
							Context.$M.modify(SettingType.AUDIO_BITRATE, bitrateText.getText());
					}
				}
			}
		});

		
		StylizedJPanel samplingRatePanel = new StylizedJPanel();
		samplingRatePanel.add(new JLabel("Taux d'echantillonnage (Hz) : "), BorderLayout.WEST);
		samplingRateText = new JTextField();
		samplingRateText.setPreferredSize(new Dimension(100, 20));
		samplingRatePanel.setToolTipText("Le taux d'echantillonage du fichier en cours d'utilisation.");
		samplingRatePanel.add(samplingRateText, BorderLayout.EAST);
		samplingRateText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					Context.$W.requestFocus();
				else {
					if(Context.$M.getCurrentFile() != null) {
						if(isChange == true) 
							Context.$M.modify(SettingType.SAMPLING_RATE, samplingRateText.getText());
					}
				}
			}
		});
		

		StylizedJPanel channelsPanel = new StylizedJPanel();
		channelsPanel.add(new JLabel("Nombre de canaux audio : "), BorderLayout.WEST);
		channelsText = new JTextField();
		channelsText.setPreferredSize(new Dimension(100, 20));
		channelsPanel.setToolTipText("Le nombre de canaux audio du fichier en cours d'utilisation.");
		channelsPanel.add(channelsText, BorderLayout.EAST);
		channelsText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					Context.$W.requestFocus();
				else {
					if(Context.$M.getCurrentFile() != null) {
						if(isChange == true) 
							Context.$M.modify(SettingType.NUMBER_AUDIO_CHANNELS, channelsText.getText());
					}
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
		if(Context.$M.getCurrentFile() != null){
			SettingsFile settings = (SettingsFile) Context.$M.getCurrentFile();
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
