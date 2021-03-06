package gui.alerts;

import java.util.*;
import exceptions.UnfindableResourceException;
import resources.*;


/**
 * [ PARAMETRES DES TEMPS D'ALERTES ET DES ALERTES. ] 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public class AlertSettings{
	/**
	 * [ TEMPS. ]
	 */
	public static long SHORT, LONG;
	
	/**
	 * [ INTERRUPTEUR. ]
	 */
	public static boolean INTERRUPTOR;

	/**
	 * INITIALISATION DES PARAMETRES.
	 */
	static {
		try {
			ResourcesManager.secureSettings();
		} catch (UnfindableResourceException e) {
			Alert.longAlert(Alert.FAILURE, e.getMessage());
		}
		
		Map<String, String> settings = IniTools.toMap(ResourceConstants.NOTIFICATION_SETTINGS);
		SHORT = Long.parseLong(settings.get("short"));
		LONG = Long.parseLong(settings.get("long"));
		INTERRUPTOR = Boolean.parseBoolean(settings.get("interruptor"));
	}
	
	/**
	 * [ SE SOUVENIR DES MODIFICATIONS DES PARAMETRES. ]
	 * 
	 * @return	Vaut true si sauvegarde reussie.
	 * 
	 * @throws UnfindableResourceException 
	 */
	public static boolean toRemember() {
		try {
			ResourcesManager.secureSettings();
		} catch (UnfindableResourceException e) {
			Alert.longAlert(Alert.FAILURE, e.getMessage());
		}
		
		Map<String, String> settings = new HashMap<String, String>();
		settings.put("short", SHORT+"");
		settings.put("long", LONG+"");
		settings.put("interruptor", INTERRUPTOR+"");
		return IniTools.toIni(settings, ResourceConstants.NOTIFICATION_SETTINGS);
	}
}

