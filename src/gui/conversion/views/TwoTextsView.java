package gui.conversion.views;
import javax.swing.JLabel;
/**
 * [ JLABEL PROPRE ET FORMATE. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class TwoTextsView extends JLabel {
	private String first, two;
	private int sizeFirst, sizeTwo;
	
	
	/**
	 *  [ CONSTRUIT UN TEXTE PROPRE ET FORMATE. ]
	 *  
	 * @param first				Premier texte a injecter. 
	 * @param sizeFirst			Police du premier texte (en px). 
	 * @param two				Second texte a injecter. 
	 * @param sizeTwo			Police du second texte (en px). 
	 */
	public TwoTextsView(String first, int sizeFirst, String two, int sizeTwo) {
		setText(firstText(first, sizeFirst, two, sizeTwo));
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
					"#two {" +
						"color: #0000FF;" +
						"font-size: "+sizeTwo+"px;" +
					"}" +
				"</style>" +
			"</head>" +
			"<body>" +
				"<div id=both>" +
					"<p id=first>" + 
						first +
					"</p>" +
					"<br>"+
					"<p id=two>" + 
						two.toUpperCase() +
					"</p>" + 
				"</div>"+
			"</body>" + 
		"</html>";
	}
	
	
	
	/**
	 * [ GENERE LE CODE HTML/CSS. ]
	 * 
	 * @param first				Premier texte a injecter. 
	 * @param sizeFirst			Police du premier texte (en px). 
	 * @param two				Second texte a injecter. 
	 * @param sizeTwo			Police du second texte (en px). 
	 * 
	 * @return	Le code HTML/CSS constiuant la vue. 
	 */
	private String firstText(String first, int sizeFirst, String two, int sizeTwo) {
		if((this.first=first)==null) throw new NullPointerException("Premier texte null !");
		if((this.two=two)==null) throw new NullPointerException("Second texte null !");
		if((this.sizeFirst=sizeFirst)<=0) throw new IllegalArgumentException("Police incorrecte !");
		if((this.sizeTwo=sizeTwo)<=0) throw new IllegalArgumentException("Police incorrecte  !");
		return text();
	}

	
	
	/**
	 * [ CHANGER LE CONTENU DU DEUXIEME TEXTE. ]
	 * 
	 * @param two			  Second texte a injecter. 
	 */
	public void $(String two) {
		if((this.two=two)==null) throw new NullPointerException("Second texte null !");
		setText(text());
	}
}
