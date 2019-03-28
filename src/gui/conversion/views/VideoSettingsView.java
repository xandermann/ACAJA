package gui.conversion.views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import files.enumerations.SettingType;
import files.files.*;
import gui.conversion.ConversionModel;
import gui.general.Context;
import gui.style.StylizedJPanel;
import wrapper.language.*;

public final class VideoSettingsView extends SettingsView{
	//=======================================================================================================================
	//=======================================================================================================================

	
	private JTextField bitrateText, fpsText;
	private JComboBox<String> formatComboBox, codecsComboBox, resolutionsComboBox;
	private SoundSettingsView ssp;
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	private void updateVideoCodecs() {
			if(formatComboBox.getSelectedItem() == null) {
				formatComboBox.setSelectedIndex(0);
				if(codecsComboBox.getSelectedItem() == null) {
					codecsComboBox.setSelectedIndex(0);
				}
			}
				
			if(Context.$M.getCurrentFile() != null) {
				String videoFormat = formatComboBox.getSelectedItem().toString();
				((Modifiable) Context.$M.getCurrentFile()).modify(SettingType.VIDEO_FORMAT, videoFormat);
				Map<String,List<String>> codecs = CodecConstants.CORRESPONDING_EXTENSION.get(videoFormat);
				String[] videoCodecs = Arrays.copyOf(codecs.keySet().toArray(), 
						codecs.keySet().toArray().length, String[].class);
				codecsComboBox.removeAllItems();
				codecsComboBox.setModel(new DefaultComboBoxModel(videoCodecs));
				codecsComboBox.setSelectedIndex(0);
				((Modifiable) Context.$M.getCurrentFile()).modify(SettingType.VIDEO_CODEC, 
						codecsComboBox.getSelectedItem().toString());
				ssp.updateAudioCodecs();
				((SettingsFile) Context.$M.getCurrentFile()).setFileExtension(videoFormat);
			}
			
	}
	
	
	
	public VideoSettingsView(SoundSettingsView setssp) {
		Context.$C(4, this);
		
		formatComboBox = new JComboBox<String>(CodecConstants.ALL_EXTENSIONS);
		ssp = setssp;
		codecsComboBox = new JComboBox<String>(CodecConstants.ALL_SUPPORTED_VIDEO_CODECS);
		StylizedJPanel formatPanel = new StylizedJPanel();
		formatPanel.setToolTipText("Le format de sortie du fichier de sortie.");
		formatPanel.add(new JLabel("Format de sortie : "), BorderLayout.WEST);
		formatPanel.add(formatComboBox,BorderLayout.EAST);
		formatComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateVideoCodecs();		
			}
		});
		
		
		StylizedJPanel codecPanel = new StylizedJPanel();
		codecPanel.add(new JLabel("Codec video : "), BorderLayout.WEST);
		codecPanel.setToolTipText("Le codec video du fichier de sortie.");
		codecPanel.add(codecsComboBox, BorderLayout.EAST);
		codecsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Context.$M.getCurrentFile() != null) {
					if(isChange == true)
						if(((JComboBox) e.getSource()).getSelectedItem() != null)
							Context.$M.modify(SettingType.VIDEO_CODEC, 
									((JComboBox) e.getSource()).getSelectedItem().toString());
				}
			}
		});

		bitrateText = new JTextField();
		StylizedJPanel bitratePanel = new StylizedJPanel();
		bitratePanel.setToolTipText("Le taux de bits par seconde de la partie video du fichier en cours d'utilisation.");
		bitratePanel.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrateText.setPreferredSize(new Dimension(100, 20));
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
							Context.$M.modify(SettingType.VIDEO_BITRATE, bitrateText.getText());
					}
				}
			}
		});
		
		
		resolutionsComboBox = new JComboBox<String>(ResolutionConstants.ALL_RESOLUTIONS);
		StylizedJPanel resolutionPanel = new StylizedJPanel();
		resolutionPanel.add(new JLabel("Resolution : "), BorderLayout.WEST);
		resolutionPanel.setToolTipText("La resolution du fichier en cours d'utilisation.");
		resolutionPanel.add(resolutionsComboBox, BorderLayout.EAST);
		resolutionsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Context.$M.getCurrentFile() != null) {
					if(isChange == true)
						Context.$M.modify(SettingType.RESOLUTION, 
								((JComboBox) e.getSource()).getSelectedItem().toString());
				}
			}
		});

		
		fpsText = new JTextField();
		StylizedJPanel fpsPanel= new StylizedJPanel();
		fpsPanel.add(new JLabel("Images par seconde (fps) : "), BorderLayout.WEST);
		fpsText.setPreferredSize(new Dimension(100, 20));
		fpsPanel.setToolTipText("Le taux d'images par seconde du fichier en cours d'utilisation.");
		fpsPanel.add(fpsText, BorderLayout.EAST);
		fpsText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					Context.$W.requestFocus();
				else {
					if(Context.$M.getCurrentFile() != null) {
						if(isChange == true) 
							Context.$M.modify(SettingType.FRAMERATE, fpsText.getText());
					}
				}
			}
		});
		
		add(formatPanel);
		add(codecPanel);
		add(bitratePanel);
		add(resolutionPanel);
		add(fpsPanel);

	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	@Override
	public void update(Observable o, Object arg) {
		isChange = false;
		if(Context.$M.getCurrentFile() != null){
		     SettingsFile settings = (SettingsFile) Context.$M.getCurrentFile();
		     formatComboBox.setSelectedItem(settings.recent(SettingType.VIDEO_FORMAT));
		     codecsComboBox.setSelectedItem(settings.recent(SettingType.VIDEO_CODEC));
		     resolutionsComboBox.setSelectedItem(settings.recent(SettingType.RESOLUTION));
			 bitrateText.setText(settings.recent(SettingType.VIDEO_BITRATE)); 
			 fpsText.setText(settings.recent(SettingType.FRAMERATE));
		}else{		 
			 formatComboBox.setSelectedIndex(0);
			 codecsComboBox.setSelectedIndex(0);
			 resolutionsComboBox.setSelectedIndex(0);
			 bitrateText.setText("");
			 fpsText.setText("");
		}
		isChange = true;
		updateVideoCodecs();
	}
	

	
	//=======================================================================================================================
	//=======================================================================================================================
}
