package gui.conversion.views;
import javax.swing.JLabel;
/**
 * [ JLABEL PROPRE ET FORMATE. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class TwoTextsView extends JLabel {
	/**
	 * [ TEXTES INJECTES. ]
	 */
	private String first, second;
	/**
	 * [ POLICES DES TEXTES. ]
	 */
	private int sizeFirst, sizeSecond;
	
	
	
	/**
	 *  [ CONSTRUIT UN TEXTE PROPRE ET FORMATE. ]
	 *  
	 * @param first					Premier texte a injecter. 
	 * @param sizeFirst				Police du premier texte (en px). 
	 * @param second				Second texte a injecter. 
	 * @param sizeSecond			Police du second texte (en px). 
	 */
	public TwoTextsView(String first, int sizeFirst, String second, int sizeSecond) {
		if((this.first=first)==null) throw new NullPointerException("Premier texte null !");
		if((this.second=second)==null) throw new NullPointerException("Second texte null !");
		if((this.sizeFirst=sizeFirst)<=0) throw new IllegalArgumentException("Police incorrecte !");
		if((this.sizeSecond=sizeSecond)<=0) throw new IllegalArgumentException("Police incorrecte  !");
		
		setText(text());
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	
	
	/**
	 * [ GENERE LE CODE HTML/CSS. ]
	 * 
	 * @return	Le code HTML/CSS constiuant la vue. 
	 */
	private String text() {
		return "<html>" + 
					"<head>" +
						"<style>" +
							"#both{" +
								"text-align: center; " +
							"}" +
							"#first {" +
								"font-size: "+sizeFirst+"px;" +
							"}" +
							"#second {" +
								"color: #0000FF;" +
								"font-size: "+sizeSecond+"px;" +
							"}" +
						"</style>" +
				   "</head>" +
				   "<body>" +
				   		"<div id=both>" +
				   			"<p id=first>" + 
				   				first +
				   			"</p>" +
				   			"<br>"+
				   			"<p id=second>" + 
				   				second.toUpperCase() +
				   			"</p>" + 
				   	   "</div>"+
				  "</body>" + 
		     "</html>";
	}
	
	
	
	/**
	 * [ CHANGER LE CONTENU DU DEUXIEME TEXTE. ]
	 * 
	 * @param second			  Second texte a injecter. 
	 */
	public void $(String second) {
		if((this.second=second)==null) throw new NullPointerException("Second texte null !");
		setText(text());
	}
}
