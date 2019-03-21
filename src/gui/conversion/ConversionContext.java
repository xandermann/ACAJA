package gui.conversion;
import javax.swing.*;
import gui.conversion.views.LibraryView;
import gui.general.GeneralContext;
/**
 * [ CONTEXTE GRAPHIQUE DANS CONVERSION. ]
 */
public final class ConversionContext extends GeneralContext{
	/**
	 * VUE DE LA LIBRAIRIE DU CONTEXTE GRAPHIQUE COURANT.
	 */
	public static LibraryView LIBRARY;
	/**
	 * VUE DES DONNEES DU FIVHIER COURANT DU CONTEXTE GRAPHIQUE COURANT.
	 */
	public static JPanel CONCERNED_FILE_VIEW;
}
