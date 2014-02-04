package directorytojson;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainClass {

	public static void main(String arg[]){	
		if (arg.length == 0) {
			System.out.println("Pass the file path");
			return;
		}
		
		FolderParser parser  = new FolderParser();		
		try {
			// Printing the JSON string to the console.
			System.out.println(parser.getJSONString(arg[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

