package resources;

import java.io.*;
import java.util.*;

/**
 * [ GERER DES FICHIERS .ini. ]  
 */
public final class IniManager {
	/**
	 * [ INI VERS MAP. ]
	 * 
	 * @param ini		Ini a convertir en map.
	 * 
	 * @return			Map issue de la conversion.
	 */
	public static Map<String, String> from(File ini) {
		if(ini==null) 
			throw new NullPointerException("ini null !");
		
		if(!ini.getName().endsWith("ini")) 
			throw new IllegalArgumentException("Attendu : un fichier ini.");
		
		if(!ini.exists()) 
			throw new IllegalArgumentException("Fichier ini inexistant !");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ini));
			Map<String, String> settings = new HashMap<String, String>();
			String line = null;
			
			while((line=reader.readLine()) != null) {
				if(!line.contains("="))
					throw new IllegalArgumentException("Fichier ini synthaxiquement incorrect.");
				String[] extracted = line.split("=");
				settings.put(extracted[0], extracted[1]);
			}
			
			reader.close();
			return settings;
		} catch (Exception e) {
			return null;
		} 
	}
	
	
	
	/**
	 * [ MAP VERS INI. ]
	 * 
	 * @param toIni		Map a convertir.
	 * 
	 * @param ini		Ini a creer.
	 * 
	 * @return 			Vaut true si conversion reussie
	 */
	public static boolean to(Map<String, String> toIni, File ini) {
		if(toIni==null) 
			throw new NullPointerException("toIni null !");
		
		if(ini==null) 
			throw new NullPointerException("ini null !");
		
		if(!ini.getName().endsWith("ini")) 
			throw new IllegalArgumentException("Attendu : un fichier ini.");
		
		if(!ini.exists()) 
			throw new IllegalArgumentException("Fichier ini inexistant !");
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(ini));
	
			Set<String> keys = toIni.keySet();
			for(String key : keys) writer.write(key+"="+toIni.get(key)+"\n");

			writer.close();
			return true;
		} catch (Exception e) {
			return false;
		} 
	}
}
