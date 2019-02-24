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

public final class SoundSettingsView extends SettingsView{
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	private JComboBox<String> codecsComboBox;
	private JTextField bitrateText, samplingRateText, channelsText;

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	public SoundSettingsView(ConversionModel model) {
		super(model);


		JPanel codecPanel = new JPanel(new FlowLayout());
		codecPanel.add(new JLabel("Codec audio : "), BorderLayout.WEST);
		codecsComboBox = new JComboBox<String>(CodecConstants.ALL_SUPPORTED_AUDIO_CODECS);
		codecPanel.add(codecsComboBox, BorderLayout.EAST);
		
		codecsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true)
						model.modify(SettingType.AUDIO_CODEC, ((JComboBox) e.getSource()).getSelectedItem().toString());
				}
			}
		});

		
		JPanel bitratePanel = new JPanel(new FlowLayout());
		bitratePanel.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrateText = new JTextField();
		bitrateText.setPreferredSize(new Dimension(300, 20));
		bitratePanel.add(bitrateText, BorderLayout.EAST);
		bitrateText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.AUDIO_BITRATE, bitrateText.getText());
				}
			}
		});

		
		JPanel samplingRatePanel = new JPanel(new FlowLayout());
		samplingRatePanel.add(new JLabel("Taux d'echantillonnage (Hz) : "), BorderLayout.WEST);
		samplingRateText = new JTextField();
		samplingRateText.setPreferredSize(new Dimension(300, 20));
		samplingRatePanel.add(samplingRateText, BorderLayout.EAST);
		samplingRateText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.SAMPLING_RATE, samplingRateText.getText());
				}
			}
		});
		

		JPanel channelsPanel = new JPanel(new FlowLayout());
		channelsPanel.add(new JLabel("Nombre de canaux audio en sortie : "), BorderLayout.WEST);
		channelsText = new JTextField();
		channelsText.setPreferredSize(new Dimension(300, 20));
		channelsPanel.add(channelsText, BorderLayout.EAST);
		channelsText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getCurrentFile() != null) {
					if(isChange == true) model.modify(SettingType.NUMBER_AUDIO_CHANNELS ,channelsText.getText());
				}
			}
		});
		
		
		add(codecPanel);
		add(bitratePanel);
		add(samplingRatePanel);
		add(channelsPanel);
		
		
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
			codecsComboBox.setSelectedItem(settings.get(SettingType.AUDIO_CODEC));
			bitrateText.setText(settings.get(SettingType.AUDIO_BITRATE));
			samplingRateText.setText(settings.get(SettingType.SAMPLING_RATE));
			channelsText.setText(settings.get(SettingType.NUMBER_AUDIO_CHANNELS));
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
