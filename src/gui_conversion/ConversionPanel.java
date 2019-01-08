package gui_conversion;

<<<<<<< HEAD

import javax.swing.*;

public class ConversionPanel {

	private JFrame window;

	private ConversionModel model;

	/** Constructeur de la classe ConversionPanel
	 *
	 * @param p_cm modele actuellement utilise dans le programme
	 */
	public ConversionPanel(ConversionModel p_cm) {
		this.model = p_cm;
	}

	/** Methode privee qui dessine le menu Fichier et son contenu
	 *
	 */
	private void drawFileMenu() {

	}

	/** Methode privee qui dessine le menu Profils et son contenu
	 *
	 */
	private void drawProfilesMenu() {

	}

	/** Methode privee qui dessine le menu Options et son contenu
	 *
	 */
	private void drawOptionsMenu() {

	}

	/**
	 *
	 */
	private void drawConvertMenu() {

	}

	/** Methode publique qui genere la fenetre de conversion et l'affiche
	 *
	 */
	public void generateConversionWindow() {
		this.window = new JFrame();
		LibraryView library = new LibraryView(this.model);
		SummaryView summarySelected = new SummaryView(this.model);
		TabsView settingsSelected = new TabsView(this.model);
		SoundSettingsPanel soundSettings = new SoundSettingsPanel(this.model);
		VideoSettingsPanel videoSettings = new VideoSettingsPanel(this.model);
		settingsSelected.add(videoSettings);
		settingsSelected.add(soundSettings);
		// Modifier le layout
		// Ajouter le menu avec ou sans les methodes privees (voir groupe)
		this.window.add(library);
		this.window.add(summarySelected);
		this.window.add(settingsSelected);
		this.window.setVisible(true);
	}

}
