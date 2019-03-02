package gui.conversion.views_controllers;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import files.enumerations.SettingType;
import files.files.SettingsFile;
import gui.conversion.model.ConversionModel;
import gui.style.StylizedJPanel;
import wrapper.language.*;

public final class VideoSettingsView extends SettingsView{
	//=======================================================================================================================
	//=======================================================================================================================

	
	private JTextField bitrateText, fpsText;
	private JComboBox<String> formatComboBox, codecsComboBox, resolutionsComboBox;
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	private void updateVideoCodecs() {
			if(model.getCurrentFile() != null && isChange == true && formatComboBox.getSelectedItem() != null) {
				String videoFormat = formatComboBox.getSelectedItem().toString();
				model.modify(SettingType.VIDEO_FORMAT, videoFormat);
				Map<String,String> codecs = CodecConstants.CORRESPONDING_EXTENSION.get(videoFormat);
				String[] videoCodecs = Arrays.copyOf(codecs.keySet().toArray(), codecs.keySet().toArray().length, String[].class);
				codecsComboBox.removeAllItems();
				codecsComboBox.setModel(new DefaultComboBoxModel(videoCodecs));
				codecsComboBox.setSelectedIndex(0);
			}
		
	}
	
	public VideoSettingsView(ConversionModel model) {
		super(model);
		formatComboBox = new JComboBox<String>(CodecConstants.ALL_EXTENSIONS);
		
		codecsComboBox = new JComboBox<String>(CodecConstants.ALL_SUPPORTED_VIDEO_CODECS);
		StylizedJPanel formatPanel = new StylizedJPanel();
		formatPanel.add(new JLabel("Format de sortie : "), BorderLayout.WEST);
		formatPanel.add(formatComboBox,BorderLayout.EAST);
		formatComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateVideoCodecs();		
			}
			
		});
		formatComboBox.setSelectedIndex(0);
		updateVideoCodecs();
		StylizedJPanel codecPanel = new StylizedJPanel();
		codecPanel.add(new JLabel("Codec video : "), BorderLayout.WEST);
		codecPanel.add(codecsComboBox, BorderLayout.EAST);
		codecsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true)
						if(((JComboBox) e.getSource()).getSelectedItem() != null)
							model.modify(SettingType.VIDEO_CODEC, ((JComboBox) e.getSource()).getSelectedItem().toString());
				}
			}
		});

		bitrateText = new JTextField();
		StylizedJPanel bitratePanel = new StylizedJPanel();
		bitratePanel.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrateText.setPreferredSize(new Dimension(100, 20));
		bitratePanel.add(bitrateText, BorderLayout.EAST);
		bitrateText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.VIDEO_BITRATE, bitrateText.getText());
				}
			}
		});
		
		
		resolutionsComboBox = new JComboBox<String>(ResolutionConstants.ALL_RESOLUTIONS);
		StylizedJPanel resolutionPanel = new StylizedJPanel();
		resolutionPanel.add(new JLabel("Resolution : "), BorderLayout.WEST);
		resolutionPanel.add(resolutionsComboBox, BorderLayout.EAST);
		resolutionsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true)
						model.modify(SettingType.RESOLUTION, ((JComboBox) e.getSource()).getSelectedItem().toString());
				}
			}
		});

		
		fpsText = new JTextField();
		StylizedJPanel fpsTextPanel= new StylizedJPanel();
		fpsTextPanel.add(new JLabel("Images par seconde (fps) : "), BorderLayout.WEST);
		fpsText.setPreferredSize(new Dimension(100, 20));
		fpsTextPanel.add(fpsText, BorderLayout.EAST);
		fpsText.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.FRAMERATE, fpsText.getText());
				}
			}
		});
		
		add(formatPanel);
		add(codecPanel);
		add(bitratePanel);
		add(resolutionPanel);
		add(fpsTextPanel);
		
	
		setSize(new Dimension(300, 400));
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	@Override
	public void update(Observable o, Object arg) {
		isChange = false;
		if(model.getCurrentFile() != null){
		     SettingsFile settings = model.getCurrentFile();
		     codecsComboBox.setSelectedItem(settings.recent(SettingType.VIDEO_CODEC));
		     resolutionsComboBox.setSelectedItem(settings.recent(SettingType.RESOLUTION));
			 bitrateText.setText(settings.recent(SettingType.VIDEO_BITRATE)); 
			 fpsText.setText(settings.recent(SettingType.FRAMERATE));
		}else{		 
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
