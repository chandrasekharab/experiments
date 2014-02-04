package directorytojson;

import java.util.Map;

import org.json.simple.JSONValue;

/**
 * Util.java
 * Contains the helper methods.
 */
public class Util {

	private static final String PROPERTIES_EXT = ".PROPERTIES";
	
	/**
	 * Checks the whether given file is properties file or not.
	 * @param fileName, valid file name.
	 * @return returns the true if it is property file else false.
	 */
	public static boolean isPropertiesFile(String fileName){
		if(fileName == null || fileName.isEmpty()){
			return false;
		}		
		return fileName.toLowerCase().endsWith(PROPERTIES_EXT.toLowerCase());
	}
	
	/**
	 * Converts the HashMap to JSONString.
	 * @param jsonMap, valid HashMap
	 * @return returns the json string.
	 */
	public  static String toJSONString(Map<String, Object> jsonMap){
		// Using the JSONValue utility to convert map to json string.
		return JSONValue.toJSONString(jsonMap);
	}
}
