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
	 * 
	 */
	private static final long serialVersionUID = -6868328034037112437L;
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
								"color: #3550a3;" +
								"font-size: "+sizeSecond+"px;" +
							"}" +
						"</style>" +
				   "</head>" +
				   "<body>" +
				   		"<div id=both>" +
				   			"<div id=first>" + 
				   				first +
				   			"</div>" +
				   			"<div id=second>" + 
				   				second.toUpperCase() +
				   			"</div>" + 
				   	   "</div>"+
				   	"<br>"+
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
