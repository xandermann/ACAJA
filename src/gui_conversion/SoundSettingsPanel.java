package gui_conversion;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
		this.setLayout(new GridLayout(5, 2));
		this.add(new JLabel("Codec utilise : "));
		this.add(new JComboBox<>());
		this.add(new JLabel("Volume en sortie (en %): "));
		this.add(new JTextField());
		this.add(new JLabel("Bitrate (kb/s) : "));
		this.add(new JTextField());
		this.add(new JLabel("Taux d'echantillonnage (Hz) : "));
		this.add(new JTextField());
		this.add(new JLabel("Nombre de canaux audio en sortie : "));
		this.add(new JTextField());
		
		//this.repaint();
	}
}
