package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SoundSettingsPanel extends JPanel {

	private ConversionModel model;
	
	public SoundSettingsPanel(ConversionModel m) {
		this.model = m;
		this.reevaluatePanel();
	}
	
	private void reevaluatePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pan_codec = new JPanel(new FlowLayout());
		pan_codec.add(new JLabel("Codec utilise : "),BorderLayout.WEST);
		JComboBox box_codec = new JComboBox();
		pan_codec.add(box_codec,BorderLayout.EAST);
		box_codec.setEnabled(false);
		this.add(pan_codec);
		
		JPanel pan_vol = new JPanel(new FlowLayout());
		pan_vol.add(new JLabel("Volume en sortie (en %): "),BorderLayout.WEST);
		JTextField volume_Text = new JTextField();
		volume_Text.setPreferredSize(new Dimension(300,20));
		pan_vol.add(volume_Text,BorderLayout.EAST);
		volume_Text.setEnabled(false);
		this.add(pan_vol);
		
		JPanel pan_bitrate = new JPanel(new FlowLayout());
		pan_bitrate.add(new JLabel("Bitrate (kb/s) : "),BorderLayout.WEST);
		JTextField bitrate_Text = new JTextField();
		bitrate_Text.setPreferredSize(new Dimension(300,20));
		pan_bitrate.add(bitrate_Text,BorderLayout.EAST);
		bitrate_Text.setEnabled(false);
		this.add(pan_bitrate);
		
		JPanel pan_echant = new JPanel(new FlowLayout());
		pan_echant.add(new JLabel("Taux d'echantillonnage (Hz) : "),BorderLayout.WEST);
		JTextField enchant_Text = new JTextField();
		enchant_Text.setPreferredSize(new Dimension(300,20));
		pan_echant.add(enchant_Text,BorderLayout.EAST);
		enchant_Text.setEnabled(false);
		this.add(pan_echant);
		
		JPanel pan_canaux = new JPanel(new FlowLayout());
		pan_canaux.add(new JLabel("Nombre de canaux audio en sortie : "),BorderLayout.WEST);
		JTextField canaux_Text = new JTextField();
		canaux_Text.setPreferredSize(new Dimension(300,20));
		pan_canaux.add(canaux_Text,BorderLayout.EAST);
		canaux_Text.setEnabled(false);
		this.add(pan_canaux);
		
		//this.repaint();
	}
}
