package gui.conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public final class SoundSettingsView extends JPanel implements Observer {
	//=======================================================================================================================
	//=======================================================================================================================

	
	private ConversionModel model;
	private JComboBox<String> codecsComboBox;
	private JTextField bitrate;
	private JTextField samplingRate;
	private JTextField channels;

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	public SoundSettingsView(ConversionModel model) {
		this.model = model;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel codecPanel = new JPanel(new FlowLayout());
		codecPanel.add(new JLabel("Codec audio : "), BorderLayout.WEST);
		codecsComboBox = new JComboBox<String>(CodecConstants.ALL_SUPPORTED_AUDIO_CODECS);
		codecPanel.add(codecsComboBox, BorderLayout.EAST);
		add(codecPanel);

		JPanel bitratePanel = new JPanel(new FlowLayout());
		bitratePanel.add(new JLabel("Bitrate (kb/s) : "), BorderLayout.WEST);
		bitrate = new JTextField();
		bitrate.setPreferredSize(new Dimension(300, 20));
		bitratePanel.add(bitrate, BorderLayout.EAST);
		add(bitratePanel);

		JPanel samplingRatePanel = new JPanel(new FlowLayout());
		samplingRatePanel.add(new JLabel("Taux d'echantillonnage (Hz) : "), BorderLayout.WEST);
		samplingRate = new JTextField();
		samplingRate.setPreferredSize(new Dimension(300, 20));
		samplingRatePanel.add(samplingRate, BorderLayout.EAST);
		add(samplingRatePanel);

		JPanel channelsPanel = new JPanel(new FlowLayout());
		channelsPanel.add(new JLabel("Nombre de canaux audio en sortie : "), BorderLayout.WEST);
		channels = new JTextField();
		channels.setPreferredSize(new Dimension(300, 20));
		channelsPanel.add(channels, BorderLayout.EAST);
		add(channelsPanel);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================

	
	@Override
	public void update(Observable o, Object arg) {
		if (model.getCurrentFile() != null) {
			HashMap<SettingType, String> settings = model.getCurrentFile().getSettings();
			codecsComboBox.setSelectedItem(settings.get(SettingType.AUDIO_CODEC));
			bitrate.setText(settings.get(SettingType.AUDIO_BITRATE));
			samplingRate.setText(settings.get(SettingType.SAMPLING_RATE));
			channels.setText(settings.get(SettingType.NUMBER_AUDIO_CHANNELS));
		}
	}
	

	//=======================================================================================================================
	//=======================================================================================================================
}
