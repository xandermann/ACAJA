package gui.conversion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import files.SettingType;
import wrapper.language.ResolutionsConstants;

public final class VideoSettingsView extends JPanel implements Observer {
	//=======================================================================================================================
	//=======================================================================================================================

	
	private ConversionModel model;
	private JTextField bitrateText;
	private JTextField fpsText;
	private JComboBox<String> codecsComboBox, resolutionsComboBox;
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public VideoSettingsView(ConversionModel model) {
		this.model = model;

		bitrateText = new JTextField();
		fpsText = new JTextField();

		setSize(new Dimension(400, 400));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel codecPanel = new JPanel();
		codecPanel.setLayout(new FlowLayout());
		codecsComboBox = new JComboBox<String>(new String[]{"mpeg4"});
		codecsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getCurrentFile() != null)
					model.modify(SettingType.VIDEO_CODEC, ((JComboBox) e.getSource()).getSelectedItem().toString());
			}
		});
		codecPanel.add(new JLabel("Codec video : "), BorderLayout.WEST);
		codecPanel.add(codecsComboBox, BorderLayout.EAST);

		ResolutionsConstants.getAllResolutions();
		
		JPanel resolutionPanel = new JPanel();
		resolutionPanel.setLayout(new FlowLayout());
		resolutionsComboBox = new JComboBox<String>(ResolutionsConstants.ALL_RESOLUTIONS);
		resolutionsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) 				
					model.modify(SettingType.RESOLUTION, ((JComboBox) e.getSource()).getSelectedItem().toString());
			}
		});
		resolutionPanel.add(new JLabel("Resolution : "), BorderLayout.WEST);
		resolutionPanel.add(resolutionsComboBox, BorderLayout.EAST);

		JPanel bitrateTextPanel = new JPanel();
		bitrateTextPanel.setLayout(new FlowLayout());
		bitrateTextPanel.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrateText.setPreferredSize(new Dimension(300, 20));
		bitrateTextPanel.add(bitrateText, BorderLayout.EAST);

		JPanel fpsTextPanel= new JPanel();
		fpsTextPanel.setLayout(new FlowLayout());
		fpsTextPanel.add(new JLabel("Images par seconde (fps) : "), BorderLayout.WEST);
		fpsText.setPreferredSize(new Dimension(300, 20));
		fpsTextPanel.add(fpsText, BorderLayout.EAST);

		add(codecPanel);
		add(resolutionPanel);
		add(bitrateTextPanel);
		add(fpsTextPanel);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	@Override
	public void update(Observable o, Object arg) {
		if(model.getCurrentFile() != null) {
		     HashMap<SettingType, String> settings = model.getCurrentFile().getSettings();
		     codecsComboBox.setSelectedItem(settings.get(SettingType.VIDEO_CODEC));
		     resolutionsComboBox.setSelectedItem(settings.get(SettingType.RESOLUTION));
			 bitrateText.setText(settings.get(SettingType.VIDEO_BITRATE)); 
			 fpsText.setText(settings.get(SettingType.FRAMERATE));
		}
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
