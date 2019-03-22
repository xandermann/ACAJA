package gui.general;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
/**
 * [ CONTEXTE GRAPHIQUE. ]
 */
public final class Context{
	/**
	 * FENETRE PRINCIPALE DU CONTEXTE GRAPHIQUE COURANT.
	 */
	public static JFrame $W;
	/**
	 * MODELE DU CONTEXTE GRAPHIQUE COURANT.
	 */
	public static GeneralModel $M;
	/**
	 * TABLEAU DE DIVERS COMPOSANTS GRAPHIQUES. 
	 */
	public static List<Object> $ = new ArrayList<Object>();
	/**
	 * 1ER ELEMENT DE LA LISTE.
	 */
	public static Object $0() {
		return $.get(0);
	}
	/**
	 * 2EME ELEMENT DE LA LISTE.
	 */
	public static Object $1() {
		return $.get(1);
	}
	/**
	 * INITIALISATEUR.
	 */
	public static void $() {
		$W = null;
		$M = null;
		$ = new ArrayList<Object>();
	}
}
