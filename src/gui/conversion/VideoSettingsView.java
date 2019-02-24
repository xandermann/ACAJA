package gui.conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import files.SettingType;
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
		JPanel codecPanel = new JPanel();
		codecPanel.setLayout(new FlowLayout());
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
		JPanel bitratePanel = new JPanel();
		bitratePanel.setLayout(new FlowLayout());
		bitratePanel.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrateText.setPreferredSize(new Dimension(300, 20));
		bitratePanel.add(bitrateText, BorderLayout.EAST);
		bitrateText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.VIDEO_BITRATE, bitrateText.getText());
				}
			}
		});
		
		
		resolutionsComboBox = new JComboBox<String>(ResolutionConstants.ALL_RESOLUTIONS);
		JPanel resolutionPanel = new JPanel();
		resolutionPanel.setLayout(new FlowLayout());
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
		JPanel fpsTextPanel= new JPanel();
		fpsTextPanel.setLayout(new FlowLayout());
		fpsTextPanel.add(new JLabel("Images par seconde (fps) : "), BorderLayout.WEST);
		fpsText.setPreferredSize(new Dimension(300, 20));
		fpsTextPanel.add(fpsText, BorderLayout.EAST);
		fpsText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.FRAMERATE, fpsText.getText());
				}
			}
		});

		
		add(codecPanel);
		add(bitratePanel);
		add(resolutionPanel);
		add(fpsTextPanel);
		
		
		setSize(new Dimension(400, 400));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
