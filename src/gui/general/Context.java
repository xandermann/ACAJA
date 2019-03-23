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
	 */
	public static JFrame $W;
	/**
	 * MODELE DU CONTEXTE GRAPHIQUE COURANT.
	 */
	public static GeneralModel $M;
	/**
	 * LISTE DE DIVERS COMPOSANTS GRAPHIQUES. 
	 * C = Context.
	 */
	public static List<Component> $C = new ArrayList<Component>();
	
	
	/**
	 * AJOUTER/MODIFIER/SUPPRIMER UN ELEMENT DE LA LISTE.
	 * 
	 * Si val = null => suppresion de l'element d'indice id.
	 */
	public static void $C(int id, Component val) {
		if(val==null) {
			if(id<$C.size())
				$C.remove(id);
		} else {
			if(id<0) 
				throw new IllegalArgumentException("id negatif !");
			if(id<$C.size())
				$C.set(id, val);
			else
				$C.add(id, val);
		}	
	}
	
	
	/**
	 * RECUPERER UN ELEMENT DE LA LISTE.
	 */
	public static Component $C(int id) {
		if(id<0) 
			throw new IllegalArgumentException("id negatif !");
		return id<$C.size() ? $C.get(id) : null;
	}
	
	
	/**
	 * INITIALISATEUR.
	 * 0 = reinitialiser le contexte graphique.
	 */
	public static void $0() {
		$W = null;
		$M = null;
		$C = new ArrayList<Component>();
	}
}
