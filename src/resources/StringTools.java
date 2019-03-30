package resources;

public final class StringTools {
	/**
	 * [ METHODE RECURSIVE POUR COMPTER LE NOMBRE D'OCCURENCES D'UNE PATTERN ENTRE DES INDICES. ]
	 * 
	 * @param asFilter		La chaine.
	 * @param pattern		La pattern en question.
	 * @param start			L'indice de debut dans la chaine.
	 * @param end			L'indice de fin dans la chaine.
	 * 
	 * @return				Le nombre d'occurences de la pattern dans la chaine entre les indices.
	 */
	public static int countOccurences(String asFilter, char pattern, int start, int end) {
		start = asFilter.indexOf(pattern, start);
		return start==-1 || start > end ? 0 : countOccurences(asFilter, pattern, start + 1, end) + 1;
	}
	
	/**
	 * [ METHODE POUR COMPTER LE NOMBRE D'OCCURENCES D'UNE PATTERN. ]
	 * 
	 * @param asFilter
	 * @param asFilter		La chaine.
	 * @param pattern		La pattern en question.
	 * 
	 * @return				Le nombre d'occurences de la pattern dans la chaine 
	 * 
	 * @see #countOccurences(String, char, int, int)
	 */
	public static int countOccurences(String asFilter, char pattern) {
		return countOccurences(asFilter, pattern, 0, asFilter.length()-1);
	}
}
