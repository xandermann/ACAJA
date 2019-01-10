package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import files.SettingsFile;

public class SoundSettingsPanel extends JPanel implements Observer{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	private ConversionModel model;
	private JTextField includeBitrate;
	private JTextField includeSamplingRate;
	private JTextField includeChannels;
	private JTextField includeVolume;
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public SoundSettingsPanel(ConversionModel m) {
		this.model = m;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pan_codec = new JPanel(new FlowLayout());
		pan_codec.add(new JLabel("Codec utilise : "),BorderLayout.WEST);
		JComboBox box_codec = new JComboBox();
		pan_codec.add(box_codec,BorderLayout.EAST);
		box_codec.setEnabled(false);
		this.add(pan_codec);
		
		JPanel pan_vol = new JPanel(new FlowLayout());
		pan_vol.add(new JLabel("Volume en sortie (en %): "),BorderLayout.WEST);
		includeVolume = new JTextField();
		includeVolume.setPreferredSize(new Dimension(300,20));
		pan_vol.add(includeVolume,BorderLayout.EAST);
		includeVolume.setEnabled(false);
		this.add(pan_vol);
		
		JPanel pan_bitrate = new JPanel(new FlowLayout());
		pan_bitrate.add(new JLabel("Bitrate (kb/s) : "),BorderLayout.WEST);
		includeBitrate = new JTextField();
		includeBitrate.setPreferredSize(new Dimension(300,20));
		pan_bitrate.add(includeBitrate,BorderLayout.EAST);
		includeBitrate.setEnabled(false);
		this.add(pan_bitrate);
		
		JPanel pan_echant = new JPanel(new FlowLayout());
		pan_echant.add(new JLabel("Taux d'echantillonnage (Hz) : "),BorderLayout.WEST);
		includeSamplingRate = new JTextField();
		includeSamplingRate.setPreferredSize(new Dimension(300,20));
		pan_echant.add(includeSamplingRate,BorderLayout.EAST);
		includeSamplingRate.setEnabled(false);
		this.add(pan_echant);
		
		JPanel pan_canaux = new JPanel(new FlowLayout());
		pan_canaux.add(new JLabel("Nombre de canaux audio en sortie : "),BorderLayout.WEST);
		includeChannels = new JTextField();
		includeChannels.setPreferredSize(new Dimension(300,20));
		pan_canaux.add(includeChannels,BorderLayout.EAST);
		includeChannels.setEnabled(false);
		this.add(pan_canaux);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(model.getCurrentFile() != null) {
			HashMap<Integer, Object> settings = model.getCurrentFile().getSettings();
			includeBitrate.setText( "" +
					(Integer) settings.get(SettingsFile.AUDIO_BITRATE));
			includeBitrate.setEnabled(true);
			includeSamplingRate.setText( "" +
					(Integer) settings.get(SettingsFile.SAMPLING_RATE));
			includeSamplingRate.setEnabled(true);
			includeChannels.setText( "" +
					(Integer) settings.get(SettingsFile.NUMBER_AUDIO_CHANNELS));
			includeChannels.setEnabled(true);
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
