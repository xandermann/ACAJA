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

import files.SettingsFile;

public final class SoundSettingsPanel extends JPanel implements Observer{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	private ConversionModel model;
	private JTextField bitrate;
	private JTextField samplingRate;
	private JTextField channels;
	private JTextField sound;
	
	
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
		sound = new JTextField();
		sound.setPreferredSize(new Dimension(300,20));
		pan_vol.add(sound,BorderLayout.EAST);
		sound.setEnabled(false);
		this.add(pan_vol);
		
		JPanel pan_bitrate = new JPanel(new FlowLayout());
		pan_bitrate.add(new JLabel("Bitrate (kb/s) : "),BorderLayout.WEST);
		bitrate = new JTextField();
		bitrate.setPreferredSize(new Dimension(300,20));
		pan_bitrate.add(bitrate,BorderLayout.EAST);
		bitrate.setEnabled(false);
		this.add(pan_bitrate);
		
		JPanel pan_echant = new JPanel(new FlowLayout());
		pan_echant.add(new JLabel("Taux d'echantillonnage (Hz) : "),BorderLayout.WEST);
		samplingRate = new JTextField();
		samplingRate.setPreferredSize(new Dimension(300,20));
		pan_echant.add(samplingRate,BorderLayout.EAST);
		samplingRate.setEnabled(false);
		this.add(pan_echant);
		
		JPanel pan_canaux = new JPanel(new FlowLayout());
		pan_canaux.add(new JLabel("Nombre de canaux audio en sortie : "),BorderLayout.WEST);
		channels = new JTextField();
		channels.setPreferredSize(new Dimension(300,20));
		pan_canaux.add(channels,BorderLayout.EAST);
		channels.setEnabled(false);
		this.add(pan_canaux);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(model.getCurrentFile() != null) {
			HashMap<Integer, Object> settings = model.getCurrentFile().getSettings();
			bitrate.setText( "" +
					(Integer) settings.get(SettingsFile.AUDIO_BITRATE));
			bitrate.setEnabled(true);
			samplingRate.setText( "" +
					(Integer) settings.get(SettingsFile.SAMPLING_RATE));
			samplingRate.setEnabled(true);
			channels.setText( "" +
					(Integer) settings.get(SettingsFile.NUMBER_AUDIO_CHANNELS));
			channels.setEnabled(true);
		}else{
			bitrate.setText("");
			bitrate.setEnabled(false);
			samplingRate.setText("");
			samplingRate.setEnabled(false);
			channels.setText("");
			channels.setEnabled(false);
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
