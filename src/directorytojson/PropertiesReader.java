package directorytojson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * PropertiesReader.java
 * Reads the properties file and converts it to HashMap.
 *
 */
public class PropertiesReader {

	/**
	 * Converts the properties to HashMap.
	 * @param filePath, valid properties file path.
	 * @return returns the HashMap of json object.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String, String> getProperties(String filePath) throws FileNotFoundException, IOException{
		
		// Load the file.
		File file = new File(filePath);
		HashMap<String,  String> propertyMap = new HashMap<String, String>();
		Properties props = new Properties();
		
		// Load the file to properties.
		props.load(new FileReader(file));
		
		for(Entry<Object, Object> x : props.entrySet()) {
			//Parse and add property to hashmap.
			propertyMap.put(x.getKey().toString(), x.getValue().toString());
		}
		return propertyMap;
	}
}
