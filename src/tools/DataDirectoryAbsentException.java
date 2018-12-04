package tools;

public class DataDirectoryAbsentException extends Throwable{
	public DataDirectoryAbsentException() {
		super("Erreur fatale : repertoire des donnees temporaires absent et impossible a recreer.");
	}
}
