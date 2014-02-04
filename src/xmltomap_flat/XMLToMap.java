package xmltomap_flat;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.*;

import java.util.Map;
import java.util.Set;


import com.sun.org.apache.xerces.internal.parsers.SAXParser;

/**
 * This class is the main class to convert XML message to a Hashmap<String, String>.
 * It contains two main method - we can pass the XML Message as a String URI as well as
 * we can also pass XML message as a String.
 * @author Sunny Jain
 * @date 07-Jun-10
 * @version 1.0
 */
public class XMLToMap extends ValueHandler {
	
	
	/**
	 * This class will convert the <b>XML FILE</b> into a hashmap.
	 * @param uri - Location of the File
	 * @return - Hashmap of XML File
	 * @throws IOException - Incase of any problem while accessing or reading the XML file.
	 * @throws SAXException - In case of any problem while parsing the file.
	 */
	public Map<String, String> convertToMap(String uri) throws IOException,
											   SAXException{
		XMLReader xmlReader = new SAXParser();
		System.out.println("Parsing of XML file - "+ uri +"\n\n");
		/*
		 * Adding the Content Handler
		 */
		ValueHandler handler = new ValueHandler();
		this.addContentHandler(handler, xmlReader);
		xmlReader.parse(uri);	
		Map<String, String> valueMap = handler.getValueMap();
		return valueMap;
	}
	
	/**
	 * This method will help to create hashmap<String, String> of any XML String.
	 * @param is - Input Stream to XML message stored in String variable.
	 * @return - Hashmap of XML.
	 * @throws IOException - Incase of any problem with inputStream.
	 * @throws SAXException - In case of any problem while parsing the file.
	 */
	public Map<String, String> convertToMap(InputStream is) throws IOException, SAXException{
		XMLReader xmlReader = new SAXParser();
		/*
		 * Adding the Content Handler
		 */
		ValueHandler handler = new ValueHandler();
		this.addContentHandler(handler, xmlReader);
		
		InputSource iSource = new InputSource(is);
		xmlReader.parse(iSource);	
		Map<String, String> valueMap = handler.getValueMap();
		return valueMap;
	}
	
	private void addContentHandler(org.xml.sax.ContentHandler handler,XMLReader parser){
		
		parser.setContentHandler(handler);
	}
	
	
	 public static void main(String...args){
		try {
			XMLToMap test = new XMLToMap();
			//test.performDemo("C:/Documents and Settings/sjain/My Documents/SRC/releases/TxReq.xml");
			String message = "<XMLdemo>"+
								"<demo1>"+
            						"<name>abc</name>"+
            						"<address>address1</address>"+
               					"</demo1>"+
            					"<demo2>"+
            						"<name>123</name>"+
            						"<address>address2</address>"+
            					"</demo2>"+
            					"</XMLdemo>";


			Map<String, String> map = test.convertToMap(new StringToStream().convert(message));
			Set<String> keys = map.keySet();
			for(String key : keys){
				System.out.println("Key ["+ key + "] value ["+ map.get(key) +"]");
			}
		} catch (IOException e) {e.printStackTrace();}
		  catch (SAXException e) {e.printStackTrace();}		
	}
	 
}


