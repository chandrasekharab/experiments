package directorytojson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * FolderParser.java
 * Parses the folders and subfolders for properties file.
 * Converts the properties file to object. 
 *
 */
public class FolderParser {

	private HashMap<String, Object> jsonMap;
	
	
	public FolderParser(){
		jsonMap = new HashMap<String, Object>();
	}
	
	/**
	 * 
	 * @param folderPath, Root folder from where parsing need to be performed.
	 * @return the json string.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getJSONString(String folderPath) throws FileNotFoundException, IOException{
		
		// Get the Map object of folders structure includes properties.
		jsonMap = parseFolder(folderPath);
		
		// Convert the Map to string.
		return Util.toJSONString(jsonMap);
	}
	
	/**
	 * Recurssive method used to parse the folders.
	 * @param folderPath
	 * @return the Map object.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private HashMap<String, Object> parseFolder(String folderPath) throws FileNotFoundException, IOException{
		File folder = new File(folderPath);
		HashMap<String, Object> jmap = new HashMap<String, Object>();
		
		// Get the list of files, it includes folders as well.
		File files[] = folder.listFiles();
		
		for (File file : files) {
						
			if(file.isDirectory()){
				
				// If the folder look for subfolders.
				jmap.put(file.getName(), parseFolder(file.getPath()));
			} else if(Util.isPropertiesFile(file.getName())){
				
				// If the file is a property file then parse it and add to map object.
				jmap.put(file.getName().substring(0, file.getName().indexOf(".properties")), PropertiesReader.getProperties(file.getPath()));
			}
		}
		
		return jmap;
	}
}
