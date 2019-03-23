package gui.general;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;
/**
 * [ CONTEXTE GRAPHIQUE. ]
 * 
 * Les attributs / methodes de cette classe
 * sont volontairement abreges pour permettre 
 * une utilisation simple a l'extrerieur de la classe.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class Context{
	/**
	 * FENETRE PRINCIPALE DU CONTEXTE GRAPHIQUE COURANT.
	 * W = Window / fenetre principale.
	 */
	public static JFrame $W;
	/**
	 * MODELE DU CONTEXTE GRAPHIQUE COURANT.
	 * M = Model.
	 */
	public static GeneralModel $M;
	/**
	 * TABELAU DE DIVERS COMPOSANTS GRAPHIQUES. 
	 * C = Context.
	 */
	public static int SIZE = 10;
	private static Component[] $C = new Component[SIZE];
	
	
	/**
	 * AJOUTER/MODIFIER/SUPPRIMER UN ELEMENT DU TABLEAU.
	 * 
	 * Si val = null => suppresion de l'element d'indice id.
	 */
	public static void $C(int id, Component val) {
		if(val==null) {
			if(id<$C.length)
				$C[id] = null;
		} else {
			if(id<0 || id>=SIZE) 
				throw new IndexOutOfBoundsException("id hors du tableau !");
			$C[id] = val;
		}	
	}
	
	
	/**
	 * RECUPERER UN ELEMENT DU TABLEAU.
	 */
	public static Component $C(int id) {
		if(id<0 || id>=SIZE) 
			throw new IndexOutOfBoundsException("id hors du tableau !");
		return $C[id];
	}
	
	
	/**
	 * INITIALISATEUR.
	 * 0 = reinitialiser le contexte graphique.
	 */
	public static void $0() {
		$W = null;
		$M = null;
		$C = new Component[SIZE];
	}
}
