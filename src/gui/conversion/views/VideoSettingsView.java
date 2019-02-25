package gui.conversion.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import files.enumerations.SettingType;
import gui.conversion.model.ConversionModel;
import gui.style.StylizedJPanel;
import wrapper.language.CodecConstants;
import wrapper.language.ResolutionConstants;

public final class VideoSettingsView extends SettingsView{
	//=======================================================================================================================
	//=======================================================================================================================

	
	private JTextField bitrateText, fpsText;
	private JComboBox<String> codecsComboBox, resolutionsComboBox;
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	public VideoSettingsView(ConversionModel model) {
		super(model);


		codecsComboBox = new JComboBox<String>(CodecConstants.ALL_SUPPORTED_VIDEO_CODECS);
		StylizedJPanel codecPanel = new StylizedJPanel();
		codecPanel.add(new JLabel("Codec video : "), BorderLayout.WEST);
		codecPanel.add(codecsComboBox, BorderLayout.EAST);
		codecsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true)
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
			public void keyPressed(KeyEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.VIDEO_BITRATE, bitrateText.getText());
				}
			}
			public void keyReleased(KeyEvent e) {}
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
			public void keyPressed(KeyEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.FRAMERATE, fpsText.getText());
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		
		
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
		     HashMap<SettingType, String> settings = model.getCurrentFile().getSettings();
		     codecsComboBox.setSelectedItem(settings.get(SettingType.VIDEO_CODEC));
		     resolutionsComboBox.setSelectedItem(settings.get(SettingType.RESOLUTION));
			 bitrateText.setText(settings.get(SettingType.VIDEO_BITRATE)); 
			 fpsText.setText(settings.get(SettingType.FRAMERATE));
		}else{		 
			 codecsComboBox.setSelectedIndex(0);
			 resolutionsComboBox.setSelectedIndex(0);
			 bitrateText.setText("");
			 fpsText.setText("");
		}
		isChange = true;
	}
	

	
	//=======================================================================================================================
	//=======================================================================================================================
}
