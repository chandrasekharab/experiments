/*
 * $Id$
 *
 * Copyright (c) 2014  Pegasystems Inc.
 * All rights reserved.
 *
 * This  software  has  been  provided pursuant  to  a  License
 * Agreement  containing  restrictions on  its  use.   The  software
 * contains  valuable  trade secrets and proprietary information  of
 * Pegasystems Inc and is protected by  federal   copyright law.  It
 * may  not be copied,  modified,  translated or distributed in  any
 * form or medium,  disclosed to third parties or used in any manner
 * not provided for in  said  License Agreement except with  written
 * authorization from Pegasystems Inc.
*/

package printer;

import java.awt.print.PrinterException;

public class MainClass {
	public static void main(String[] args) {
		
		String text = "The Java Print Service API, introduced in v1.4, allows printing on all Java platforms including those requiring a small footprint, such as a J2ME profile, but also supports the java.awt.print.PrinterJob API introduced in J2SE v1.2. The Java Print Service API includes an extensible print attribute set based on the standard attributes specified in the Internet Printing Protocol (IPP) 1.1 from the IETF. With the attributes, client and server applications can discover and select printers that have the capabilities specified by the attributes. In addition to the included StreamPrintService, which allows applications to transcode data to different formats, third parties can dynamically install their own print services through the Service Provider Interface.\r\n" + 
				"\r\n" + 
				"API Specification\r\n" + 
				"The Java Print Service API consists of these four packages:\r\n" + 
				"\r\n" + 
				"javax.print: Provides the principal classes and interfaces for the Java Print Service API\r\n" + 
				"javax.print.attribute: Provides classes and interfaces that describe the types of Java Print Service attributes and how they can be collected into attribute sets\r\n" + 
				"javax.print.attribute.standard: Contains classes defining specific printing attributes\r\n" + 
				"javax.print.event: Contains event classes and listener interfaces for monitoring print services and the progress of a specific print job\r\n" + 
				"Tutorials and Programmer's Guides\r\n" + 
				"The Java Print Service User Guide describes the Java Print Service in more detail and demonstrates how to use the Java Print Service API to:\r\n" + 
				"\r\n" + 
				"Discover and select print services based on their capabilities\r\n" + 
				"Specify the format of print data\r\n" + 
				"Submit print jobs to services that support the document type to be printed.\r\n" + 
				"More Information\r\n" + 
				"See the Printing lesson in 2D Graphics trail of the Java Tutorials.";
		
		
		/*Printable print = new DefaultPrintable(text);		
		PrinterService ps = new PrinterService("CutePDF", 1, print);*/
		try {
			PrinterService.print(text, "CutePDF");
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
