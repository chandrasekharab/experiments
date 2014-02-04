package xmltomap_flat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * This class will help to convert any input String to inputStream.
 * Main usage of this class is to convert the response XML message to input
 * stream so that parsing will be easy.
 * @author Sunny Jain
 * @Date 07-Jun-10
 * @version 1.0
 */
public class StringToStream {
	
	/**
	 * This method will convert any String into InputStream.
	 * @param message - the String whose value to be converted into Stream.
	 * @return - InputStream to passed String.
	 */
    public InputStream convert(String message){
                
        /*
         * Convert String to InputString using ByteArrayInputStream class.
         * This class constructor takes the string byte array which can be
         * done by calling the getBytes() method.
         */
    	InputStream is = new ByteArrayInputStream(message.getBytes( ));
         return is;
    }
}
